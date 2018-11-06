package com.jagadeeshposni.amazon.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class s3APIApplication {

    public static void main(String args[]) throws InterruptedException {

        AWSCredentials credentials = new BasicAWSCredentials("AKIAI36LDMONC6OHUW7A", "rrwQ0XSCuQ46ecszbsDvv7TJYZ44r1DhXTXH/E0P");

        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_SOUTH_1)
                .build();

        List<Bucket> buckets = s3Client.listBuckets();
        for(Bucket bucket: buckets){
            System.out.println("bucket: " + bucket.getName());
        }

        Thread.sleep(5000);

        String bucketName = "posni";
        if(s3Client.doesBucketExistV2(bucketName)){
            System.out.println(bucketName + " bucket Exists....");
        }
        SpringApplication.run(s3APIApplication.class, args);
    }
}
