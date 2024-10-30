package com.fengshui.corebusiness.resource;

import com.fengshui.common.services.TransactionService;
import com.fengshui.common.shared.Request.Transaction.CreateTransactionRequestModel;
import com.fengshui.common.shared.Request.Transaction.GetTransactionRequestModel;
import com.fengshui.common.shared.Response.Transaction.CreateTransactionResponseModel;
import com.fengshui.common.shared.Response.Transaction.GetTransactionResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping
@ComponentScan(basePackages = "com.fengshui.common.services")

public class TransactionResource {
    @Autowired
    private TransactionService transactionService;

    @PostMapping(path = "/create-transaction", consumes = {"application/json"})
    public ResponseEntity<CreateTransactionResponseModel> createTransaction(@ModelAttribute CreateTransactionRequestModel requestModel) throws Exception {
        return this.transactionService.creatTransaction(requestModel);
    }

    @PostMapping(value = "/get-transaction", consumes = {"application/json"})
    public ResponseEntity<GetTransactionResponseModel> getTransaction(@ModelAttribute GetTransactionRequestModel requestModel) throws Exception {
        return this.transactionService.getTransaction(requestModel);
    }

}
