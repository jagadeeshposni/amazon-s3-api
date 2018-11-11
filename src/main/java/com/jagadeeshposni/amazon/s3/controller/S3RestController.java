package com.jagadeeshposni.amazon.s3.controller;

import com.jagadeeshposni.amazon.s3.service.AwsS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class S3RestController {

    @Autowired
    private AwsS3Service awsS3Service;

    @GetMapping("s3/test")
    public ResponseEntity<String> testS3Connection() {
        return new ResponseEntity<>(awsS3Service.testS3(), HttpStatus.OK);

    }

    @PostMapping("/s3/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        if (awsS3Service.uploadMultipartFile(multipartFile)) {
            return new ResponseEntity<String>("success", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Upload failed", HttpStatus.I_AM_A_TEAPOT);
        }


    }
}
