package edu.sharif.surveyBackend.service;

import java.io.File;
import java.io.InputStream;

import edu.sharif.surveyBackend.api.rest.upload.exception.ImageUploadException;

/**
 * Interface for handling storage requirements
 */
public interface IStorageService {

    /**
     * Upload catalogue image to storage service
     * @param skuNumber
     * @param contentType
     * @param image
     * @throws ImageUploadException
     */
    public void uploadCatalogueImage(String skuNumber, String contentType, File image) throws ImageUploadException;
}
