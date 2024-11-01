package com.fengshui.common.repository.postgresql.mapper;

import com.fengshui.common.repository.postgresql.dto.PaymentDTO;
import com.fengshui.common.repository.postgresql.entities.PaymentEntity;

public class PaymentMapper {
    public static PaymentDTO toDTO(PaymentEntity payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .user(AppUserMapper.toDTO(payment.getUser()))
                .orderCode(payment.getOrderCode())
                .amount(payment.getAmount())
                .currency(payment.getCurrency())
                .checkoutUrl(payment.getCheckoutUrl())
                .paymentLinkId(payment.getPaymentLinkId())
                .note(payment.getNote())
                .createdAt(payment.getCreatedAt())
                .build();
    }
}
