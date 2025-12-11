package com.example.multipart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponse {
    private boolean success;
    private String message;
    private String fileName;
    private String originalFileName;
    private String contentType;
    private long size;
    private double sizeInMB;
    private String checksum;
    private LocalDateTime uploadTimestamp;
}
