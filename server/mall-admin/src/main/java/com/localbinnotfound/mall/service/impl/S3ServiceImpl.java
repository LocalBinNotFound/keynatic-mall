package com.localbinnotfound.mall.service.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.localbinnotfound.mall.service.S3Service;
import com.localbinnotfound.mall.dto.S3PolicyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class S3ServiceImpl implements S3Service {
    @Autowired
    private AmazonS3 s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3PolicyResult policy() {
        String datePrefix = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String fileName = UUID.randomUUID() + ".jpg";
        String objectKey = "uploads/" + datePrefix + "/" + fileName;

        Date expiration = new Date();
        long expTimeMillis = System.currentTimeMillis() + 3600000;
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, objectKey)
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(expiration);

        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

        S3PolicyResult response = new S3PolicyResult();
        response.setDir("uploads/" + datePrefix + "/");
        response.setHost(url.toString());
        response.setExpire(expTimeMillis / 1000);

        return response;
    }
}
