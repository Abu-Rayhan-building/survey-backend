package edu.sharif.surveyBackend.service.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.NumberFormat;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import edu.sharif.surveyBackend.service.IStorageService;

@ApplicationScoped
public class FileStorageService implements IStorageService {

    private final Path fileStorageLocation;
    String p = "/tmp/survey_backend/files";

    public FileStorageService() {
	this.fileStorageLocation = Paths.get(this.p).toAbsolutePath()
		.normalize();

	try {
	    Files.createDirectories(this.fileStorageLocation);
	} catch (final Exception ex) {
	    throw new FileStorageException(
		    "Could not create the directory where the uploaded files will be stored.",
		    ex);
	}
    }

    private Response download(final String fileName) {
	final String fileLocation = this.p + fileName;
	Response response = null;
	final NumberFormat myFormat = NumberFormat.getInstance();
	myFormat.setGroupingUsed(true);

	// Retrieve the file
	final File file = new File(this.p + fileName);
	if (file.exists()) {
	    final ResponseBuilder builder = Response.ok(file);
	    builder.header("Content-Disposition",
		    "attachment; filename=" + file.getName());
	    response = builder.build();

	    final long file_size = file.length();

	} else {

	    response = Response.status(404)
		    .entity("FILE NOT FOUND: " + fileLocation)
		    .type("text/plain").build();
	}

	return response;
    }

    @Override
    public void uploadCatalogueImage(final String name,
	    final String contentType, final File file) {
	// Normalize file name
	final String fileName = file.getName();

	try {
	    // Check if the file's name contains invalid characters
	    if (fileName.contains("..")) {
		throw new FileStorageException(
			"Sorry! Filename contains invalid path sequence "
				+ fileName);
	    }

	    // Copy file to the target location (Replacing existing file with
	    // the same name)
	    final Path targetLocation = this.fileStorageLocation
		    .resolve(fileName);
	    Files.copy(new FileInputStream(file), targetLocation,
		    StandardCopyOption.REPLACE_EXISTING);

	} catch (final IOException ex) {
	    throw new FileStorageException(
		    "Could not store file " + fileName + ". Please try again!",
		    ex);
	}
    }
}