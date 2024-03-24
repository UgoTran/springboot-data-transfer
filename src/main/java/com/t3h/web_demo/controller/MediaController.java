package com.t3h.web_demo.controller;

import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
public class MediaController {

    public static final String MINIO_API_URL = "http://localhost:9000";
    public static final String MINIO_USRE = "minioadmin";
    public static final String MINIO_KEY = "minioadmin";
    public static final String MINIO_BUCKET_IMG = "eshop-media";

    /**
     * MultipartFile: đại diện 1 file đẩy lên, param: file
     */
    @PostMapping("/api/v1/upload")
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (file == null){
            return ResponseEntity.badRequest().body("file empty");
        }

        // tich hop minio
        MinioClient minioClient = MinioClient.builder()
                .endpoint(MINIO_API_URL) // cổ̃ng API
                .credentials(MINIO_USRE, MINIO_KEY)
                .build();
        // đẩy file vào bucket
        // tthay đôi file name tự đông + file extension -> tránh trùng tên file
        String objectName = UUID.randomUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(MINIO_BUCKET_IMG)
                        .object(objectName)
                        .stream(file.getInputStream(), file.getSize(), -1) // MultipartFile chứa luồng byte, nên phải dùng stream đẩy binary data
                        .build());
        log.info("đẩy file thành công {}", objectName);


        return ResponseEntity.ok(
                Map.of(
                        "fileName", file.getOriginalFilename(),
                        "size", file.getSize(),
                        "msg", "đẩy thành công file",
                        "newName", objectName
                )
        );
    }
}
