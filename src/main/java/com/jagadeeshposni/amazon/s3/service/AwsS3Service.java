package com.jagadeeshposni.amazon.s3.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.jagadeeshposni.amazon.s3.configuration.AwsS3Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AwsS3Service {

    @Autowired
    private AwsS3Configuration awsConfig;

    public String testS3() throws InterruptedException {
        AmazonS3 s3Client = awsConfig.getAwsS3Client(Regions.AP_SOUTH_1.getName());
        List<Bucket> buckets = s3Client.listBuckets();
        for(Bucket bucket: buckets){
            System.out.println("bucket: " + bucket.getName());
        }

        Thread.sleep(5000);

        String bucketName = "posni";
        if(s3Client.doesBucketExistV2(bucketName)){
            return bucketName + " bucket Exists....";
        }

        return bucketName + "Does not exist..";
    }

}
