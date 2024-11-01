package com.fengshui.common.services.impl;

import com.fengshui.common.aws.S3Client.S3Client;
import com.fengshui.common.repository.postgresql.ITransactionRepository;
import com.fengshui.common.repository.postgresql.dto.TransactionDTO;
import com.fengshui.common.repository.postgresql.mapper.TransactionMapper;
import com.fengshui.common.services.TransactionService;
import com.fengshui.common.shared.Request.Transaction.CreateTransactionRequestModel;
import com.fengshui.common.shared.Request.Transaction.GetTransactionRequestModel;
import com.fengshui.common.shared.Response.Transaction.CreateTransactionResponseModel;
import com.fengshui.common.shared.Response.Transaction.GetTransactionResponseModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    S3Client s3Client;

    @Autowired
    ITransactionRepository iTransactionRepository;

    @Override
    public ResponseEntity<CreateTransactionResponseModel> creatTransaction(CreateTransactionRequestModel requestModel) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<GetTransactionResponseModel> getTransaction(GetTransactionRequestModel requestModel) {
        GetTransactionResponseModel response;
        List<TransactionDTO> transactionList = this.iTransactionRepository.findAll().stream().map(TransactionMapper::toDTO).toList();
        response = new GetTransactionResponseModel(false, transactionList, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}