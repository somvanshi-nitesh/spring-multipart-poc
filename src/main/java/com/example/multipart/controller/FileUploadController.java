package com.example.multipart.controller;

import com.example.multipart.dto.FileUploadResponse;
import com.example.multipart.service.FileProcessingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
@Tag(name = "File Upload", description = "File upload endpoint for files >= 3MB")
public class FileUploadController {

    private final FileProcessingService fileProcessingService;

    /**
     * Upload a single file - handles files >= 3MB (up to 50MB)
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload a file (supports 3MB+)", description = "Upload files up to 50MB")
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            log.info("Uploading file: {} ({} bytes)", file.getOriginalFilename(), file.getSize());

            if (file.isEmpty()) {
                throw new IllegalArgumentException("File is empty");
            }

            FileUploadResponse response = fileProcessingService.processFile(file);
            log.info("File uploaded successfully: {} ({} MB)", file.getOriginalFilename(), response.getSizeInMB());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error uploading file: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to upload file: " + e.getMessage(), e);
        }
    }
}
