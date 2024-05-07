package com.localbinnotfound.mall.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Value("${aws.s3.access-key-id}")
    private String AWS_S3_ACCESSKEYID;
    @Value("${aws.s3.secret-access-key}")
    private String AWS_S3_SECRETACCESSKEY;
    @Value("${aws.s3.region}")
    private String REGION;

    @Bean
    public AmazonS3 s3client(){
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(AWS_S3_ACCESSKEYID, AWS_S3_SECRETACCESSKEY);
        return AmazonS3ClientBuilder.standard()
                .withRegion(REGION)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }
}
