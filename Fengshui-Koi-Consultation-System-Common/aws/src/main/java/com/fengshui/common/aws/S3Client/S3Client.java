package com.fengshui.common.aws.S3Client;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface S3Client {
    public void uploadFileTos3bucket(String folder, MultipartFile multipartFile) throws IOException;

    public String uploadImage(String folder, MultipartFile multipartFile) throws IOException;
}
