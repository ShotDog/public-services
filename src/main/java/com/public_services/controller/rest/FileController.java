package com.public_services.controller.rest;

import com.public_services.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/{id}")
    public ResponseEntity<HttpStatus> upload(@PathVariable("id") Long id, MultipartFile file) throws IOException {
        fileService.uploadFile(id, file.getBytes());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> download(@PathVariable("id") Long id) {
        byte[] content = fileService.downloadFile(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-word"));
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }

}
