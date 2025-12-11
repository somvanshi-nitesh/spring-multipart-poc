package com.example.multipart.service;

import com.example.multipart.dto.FileUploadResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Service
public class FileProcessingService {

    /**
     * Process file and return metadata
     * Uses streaming for memory efficiency with large files
     */
    public FileUploadResponse processFile(MultipartFile file) throws IOException {
        log.debug("Processing file: {} (size: {} bytes)", file.getOriginalFilename(), file.getSize());
        
        return FileUploadResponse.builder()
                .success(true)
                .message("File uploaded successfully")
                .fileName(file.getOriginalFilename())
                .originalFileName(file.getOriginalFilename())
                .contentType(file.getContentType())
                .size(file.getSize())
                .sizeInMB(file.getSize() / (1024.0 * 1024.0))
                .checksum("N/A")
                .uploadTimestamp(LocalDateTime.now())
                .build();
    }
}
