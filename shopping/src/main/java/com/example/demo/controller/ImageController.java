package com.example.demo.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    // 保存先ディレクトリの絶対パスを指定
    private final String IMAGE_DIR = "src/main/resources/static/image/";

    @GetMapping("/uploaded-images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
        	if (imageName.equals("icon.png")) {
        		imageName = "icon/icon.png";
        	}
        	else if(imageName.startsWith("bottan")) {
        		imageName = "bottan/" + imageName;
        	}
        	// ファイルパスを組み立てる
            Path imagePath = Paths.get(IMAGE_DIR + imageName).normalize();

            // ファイルをリソースとして読み込む
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                // コンテンツタイプを自動判定
                String contentType = MediaType.IMAGE_PNG_VALUE; // デフォルト
                if (imageName.endsWith(".jpg") || imageName.endsWith(".jpeg")) {
                    contentType = MediaType.IMAGE_JPEG_VALUE;
                } else if (imageName.endsWith(".gif")) {
                    contentType = MediaType.IMAGE_GIF_VALUE;
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + imageName + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

