package com.public_services.service;

public interface FileService {

    void uploadFile(Long id, byte[] file);

    byte[] downloadFile(Long id);

}
