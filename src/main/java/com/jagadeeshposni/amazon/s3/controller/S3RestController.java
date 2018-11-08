package com.jagadeeshposni.amazon.s3.controller;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.jagadeeshposni.amazon.s3.configuration.AwsS3Configuration;
import com.jagadeeshposni.amazon.s3.service.AwsS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
public class S3RestController {

    @Autowired
    private AwsS3Service awsS3Service;

    @PostMapping("/s3/upload")
    public ResponseEntity<String> upload(@RequestBody File uploadFile){

        System.out.println("hello");

        return new ResponseEntity<String>("success", HttpStatus.OK);

    }

    @GetMapping("s3/test")
    public ResponseEntity<String> testS3Connection(){
        try {
            return new ResponseEntity<>(awsS3Service.testS3(), HttpStatus.OK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;

    }
}
