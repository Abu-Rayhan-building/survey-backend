package edu.sharif.surveyBackend.api.rest.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import edu.sharif.surveyBackend.api.rest.upload.error.ErrorCodes;
import edu.sharif.surveyBackend.api.rest.upload.exception.ImageUploadException;
import edu.sharif.surveyBackend.api.rest.upload.exception.ResourceNotFoundException;
import edu.sharif.surveyBackend.mgr.user.UserMgr;
import edu.sharif.surveyBackend.model.user.User;
import edu.sharif.surveyBackend.service.storage.FileStorageService;

import org.apache.tika.Tika;
import org.apache.tika.io.IOUtils;
import org.apache.tika.mime.MimeTypes;

@Path("api/")
@Tag(name = "upload", description = "upload endpoint")
@PermitAll
public class ImageUploadAPI {

    @Inject
    UserMgr userMgr;

    @Inject
    FileStorageService storageService;

    @POST
    @Path("upload/")
    public Response uploadImage(@PathParam(value = "sku") Long skuNumber,
	    InputStream inputStream)
	    throws ResourceNotFoundException, Exception {
	// Validate skuNumber by Getting catalogue item by sku. If not
	// available, resource not found will be thrown.
	User u = userMgr.findById(skuNumber);
	if (u == null)
	    throw new ResourceNotFoundException("user not found");

	// Create temp file from the uploaded image input stream
	File tempFile = createTempFile(skuNumber, inputStream);

	// Validate if the uploaded file is of type image. Else throw error
	Tika tika = new Tika();
	String mimeType = tika.detect(tempFile);
	if (!mimeType.contains("image")) {
	    throw new ImageUploadException(
		    ErrorCodes.ERR_IMAGE_UPLOAD_INVALID_FORMAT,
		    String.format(
			    "File uploaded for SKU:%s is not valid image format",
			    skuNumber));
	}

	// Upload the file to storage service
	storageService.uploadCatalogueImage("user" + skuNumber, mimeType,
		tempFile);

	return Response.status(Response.Status.CREATED).build();
    }

    private File createTempFile(Long skuNumber, InputStream inputStream)
	    throws ImageUploadException {
	try {
	    File tempFile = File.createTempFile("user" + skuNumber, ".tmp");
	    tempFile.deleteOnExit();

	    FileOutputStream out = new FileOutputStream(tempFile);
	    IOUtils.copy(inputStream, out);

	    return tempFile;
	} catch (Exception e) {
	    throw new ImageUploadException(String.format(
		    "Error occurred while creating temp file for uploaded image : %s",
		    skuNumber), e);
	}
    }
}