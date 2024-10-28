package com.fengshui.common.services;


import com.fengshui.common.shared.Request.Transaction.CreateTransactionRequestModel;
import com.fengshui.common.shared.Request.Transaction.GetTransactionRequestModel;

import com.fengshui.common.shared.Response.Transaction.CreateTransactionResponseModel;
import com.fengshui.common.shared.Response.Transaction.GetTransactionResponseModel;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
    public ResponseEntity<CreateTransactionResponseModel> creatTransaction(CreateTransactionRequestModel requestModel);
    public ResponseEntity<GetTransactionResponseModel> getTransaction(GetTransactionRequestModel requestModel);

}
