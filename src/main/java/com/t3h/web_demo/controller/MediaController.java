package com.t3h.web_demo.controller;

import com.t3h.web_demo.storage.entity.UserEntity;
import com.t3h.web_demo.storage.repository.UserRepository;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
public class MediaController {

    public static final String MINIO_API_URL = "http://localhost:9000";
    public static final String MINIO_USRE = "minioadmin";
    public static final String MINIO_KEY = "minioadmin";
    public static final String MINIO_BUCKET_IMG = "eshop-media";
    private static final String AVATAR_PATH = "avatar";
    private static final String MINIO_UI_HOST = "http://localhost:9000/";

    @Autowired
    UserRepository userRepository;

    /**
     * MultipartFile: đại diện 1 file đẩy lên, param: file
     */
    @PostMapping("/api/v1/upload")
    public ResponseEntity upload(@RequestParam("file") MultipartFile file,
                                 @RequestParam String username) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        if (file == null) {
            return ResponseEntity.badRequest().body("file empty");
        }

        // 1.  tich hop minio
        MinioClient minioClient = MinioClient.builder()
                .endpoint(MINIO_API_URL) // cổ̃ng API
                .credentials(MINIO_USRE, MINIO_KEY)
                .build();
        // 2. đẩy file vào bucket
        // 3. tthay đôi file name tự đông + file extension -> tránh trùng tên file
        String objectName = UUID.randomUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        String path = AVATAR_PATH + "/" + objectName;
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(MINIO_BUCKET_IMG)
                        .object(path)
                        .stream(file.getInputStream(), file.getSize(), -1) // MultipartFile chứa luồng byte, nên phải dùng stream đẩy binary data
                        .build());
        log.info("đẩy file thành công {}", objectName);
        // 4. luu vào DB: path + tên file theo một object, gia su user ton tai
        // vd: 1 product - * file media
        // vd: 1 user - 1 file img

        UserEntity user = userRepository.findByUserName(username);
        user.setAvatar(MINIO_BUCKET_IMG + "/" + path);
        userRepository.save(user);
        log.info("user info: {}", user);

        return ResponseEntity.ok(
                Map.of(
                        "fileName", file.getOriginalFilename(),
                        "size", file.getSize(),
                        "msg", "đẩy thành công file",
                        "newName", objectName,
                        "mediaUrl", MINIO_UI_HOST + user.getAvatar()
                )
        );
    }

    /**
     * @param objectType: tên bảng/entity -> điều hướng bảng đó, switch case
     * @param key:        gtṛi tìm kiếm theo bảng đó
     * @return
     */
    @GetMapping("/api/v1/media")
    public ResponseEntity<Map> getMediaFile(@RequestParam String objectType,
                                            @RequestParam String key) {

        Map media = new HashMap();
        if ("USER".equals(objectType)) {
            // tìm trong bảng user
            UserEntity user = userRepository.findByUserName(key);
            media.put("url", MINIO_UI_HOST + user.getAvatar());
            return ResponseEntity.ok(media);

        } else if ("PRODUCT".equals(objectType)) {
            // tìm trong bảng user
        }

        return ResponseEntity.notFound().build();
    }

    // /api/v1/product?id=1
    // kq
    /*
    {
        id:xxx
        name:xxx
        qty:xxx
        des:xxx
        photoUrl:xxx
        photoUrl: [x1, x2,x3 ]
    }
     */
}
