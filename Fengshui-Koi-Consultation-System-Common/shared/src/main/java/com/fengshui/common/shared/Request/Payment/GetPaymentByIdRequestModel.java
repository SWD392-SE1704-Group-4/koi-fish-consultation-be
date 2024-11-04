package com.fengshui.common.shared.Request.Payment;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GetPaymentByIdRequestModel {
    UUID paymentId;
}
