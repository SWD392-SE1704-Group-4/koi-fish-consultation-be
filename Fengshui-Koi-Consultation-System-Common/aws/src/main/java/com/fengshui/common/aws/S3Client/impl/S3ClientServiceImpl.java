package com.fengshui.common.aws.S3Client.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Service
public class S3ClientServiceImpl {
    private AmazonS3 s3Client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.s3_cloudfront_distribution_url}")
    private String s3CloudfrontDistributionUrl;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;
    public static String cloudfrontUrl;
    @PostConstruct
    private void initializeAmazon() {
        cloudfrontUrl = this.s3CloudfrontDistributionUrl;
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion("ap-southeast-1")
                .build();
    }
    private void uploadFileTos3bucket(String folder, MultipartFile multipartFile) throws IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = dateTimeFormatter.format(LocalDate.now());
        String filePath = "";
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());
        filePath = folder+"/"+multipartFile.getOriginalFilename();
        s3Client.putObject(bucketName, filePath, multipartFile.getInputStream(), objectMetadata);
    }
    public String uploadImage(String folder, MultipartFile multipartFile) throws IOException {
        String fileUrl = folder+"/"+multipartFile.getOriginalFilename();
        try{
            uploadFileTos3bucket(folder, multipartFile);
            return fileUrl;
        }catch (Exception e){
            return null;
        }finally {
            return fileUrl;
        }
    }
}
