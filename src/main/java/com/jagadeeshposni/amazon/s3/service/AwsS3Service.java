package com.jagadeeshposni.amazon.s3.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.jagadeeshposni.amazon.s3.configuration.AwsS3Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class AwsS3Service {

    @Autowired
    private AwsS3Configuration awsConfig;

    public String testS3() {
        AmazonS3 s3Client = awsConfig.getAwsS3Client(Regions.AP_SOUTH_1.getName());
        List<Bucket> buckets = s3Client.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println("bucket: " + bucket.getName());
        }


        String bucketName = "posni";
        if (s3Client.doesBucketExistV2(bucketName)) {
            return bucketName + " bucket Exists....";
        }

        return bucketName + "Does not exist..";
    }

    public Boolean uploadMultipartFile(MultipartFile multipartFile) throws IOException {

        try {
            AmazonS3 s3Client = awsConfig.getAwsS3Client(Regions.AP_SOUTH_1.getName());

            byte[] byteArray = multipartFile.getBytes();

            InputStream fileStream = new ByteArrayInputStream(byteArray);

            ObjectMetadata s3ObjectMetadata = new ObjectMetadata();
            s3ObjectMetadata.addUserMetadata("sampleKey", "sampleValue");
            s3ObjectMetadata.setContentType(multipartFile.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest("posni",
                    multipartFile.getOriginalFilename(),
                    fileStream,
                    s3ObjectMetadata).withCannedAcl(CannedAccessControlList.PublicRead);

            s3Client.putObject(putObjectRequest);
        } catch (AmazonServiceException e) {

            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
            return false;
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
            return false;
        }


        return true;
    }
}
