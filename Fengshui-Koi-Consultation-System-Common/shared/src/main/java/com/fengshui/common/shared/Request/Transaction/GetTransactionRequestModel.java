package com.fengshui.common.shared.Request.Transaction;

import com.fengshui.common.shared.Model.PagingOption;
import com.fengshui.common.shared.Model.SortOption;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class GetTransactionRequestModel implements Serializable {
    String keyword;
    List<UUID> transactionIDS;
    PagingOption pagingOption;
    SortOption sortOption;
}
