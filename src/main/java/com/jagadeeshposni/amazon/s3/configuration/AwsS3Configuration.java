package com.jagadeeshposni.amazon.s3.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.util.Map;

@Component
public class AwsS3Configuration {

    private static Log logger = LogFactory.getFactory().getInstance(AwsS3Configuration.class);
    @Autowired
    private YAMLConfig ymlConfig;

    private AWSCredentials getCredentials() {

        Map<String, String> aws = ymlConfig.getAws();
        String awsAccessKeyId = new String(Base64Utils.decodeFromString(aws.get("aws_access_key_id")));
        String awsSecretKey = new String(Base64Utils.decodeFromString(aws.get("aws_secret_key")));
        return new BasicAWSCredentials(awsAccessKeyId, awsSecretKey);

    }

    public AmazonS3 getAwsS3Client(String region) {

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(getCredentials()))
                .withRegion(region)
                .build();
    }

}
