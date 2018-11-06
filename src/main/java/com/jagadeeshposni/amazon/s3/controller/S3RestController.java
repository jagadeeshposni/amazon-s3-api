package com.jagadeeshposni.amazon.s3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class S3RestController {

    @PostMapping("/s3/upload")
    public ResponseEntity<String> upload(@RequestBody File uploadFile){

        uploadFile.

    }
}
