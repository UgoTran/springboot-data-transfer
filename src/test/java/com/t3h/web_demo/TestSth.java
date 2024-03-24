package com.t3h.web_demo;

import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class TestSth {
    public static void main(String[] args) {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            // cổ̃ng WEB UI khác cổng UI
            MinioClient minioClient = MinioClient.builder()
                    .endpoint("http://localhost:9000") // cổ̃ng API
                    .credentials("minioadmin", "minioadmin")
                    .build();
            // đẩy file vào bucket
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("eshop-media")
                            .object("test.png")
                            .filename("/home/ugo/Downloads/girl.jpg")
                            .build());
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
