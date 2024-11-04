package com.fengshui.common.repository.postgresql.enums;

public enum PaymentStatus {
    SUCCESS(0),
    FAILED(1);

    private final int type;

    PaymentStatus(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static PaymentStatus fromType(int type) {
        for (PaymentStatus t : PaymentStatus.values()) {
            if (t.getType() == type) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid type: " + type);
    }
}
