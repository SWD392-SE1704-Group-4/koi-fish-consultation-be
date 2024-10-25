package com.fengshui.common.shared.Response.Transaction;
import com.fengshui.common.repository.postgresql.dto.TransactionDTO;
import com.fengshui.common.shared.Response.BaseResponseModel;
import lombok.experimental.SuperBuilder;

@SuperBuilder

public class CreateTransactionResponseModel extends BaseResponseModel {
    public CreateTransactionResponseModel(boolean has_error, TransactionDTO payload,Object error) {
        super(has_error, payload, error);
    }
}
