package com.fengshui.common.services.impl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.fengshui.common.aws.S3Client.S3Client;
import com.fengshui.common.repository.postgresql.ITransactionRepository;
import com.fengshui.common.repository.postgresql.dto.TransactionDTO;
import com.fengshui.common.services.TransactionService;
import com.fengshui.common.shared.Request.Transaction.CreateTransactionRequestModel;
import com.fengshui.common.shared.Request.Transaction.GetTransactionRequestModel;
import com.fengshui.common.shared.Response.Transaction.CreateTransactionResponseModel;
import com.fengshui.common.shared.Response.Transaction.GetTransactionResponseModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
@ComponentScan(basePackages = "com.fengshui.common")
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    S3Client s3Client;

    @Autowired
    ITransactionRepository iTransactionRepository;

    @Override
    @Transactional
    public ResponseEntity<CreateTransactionResponseModel> getTransaction(GetTransactionRequestModel requestModel) {
        GetTransactionResponseModel response;
        List<TransactionDTO> transactionList = this.iTransactionRepository.findAll().stream().map(Transaction)
    }
}
