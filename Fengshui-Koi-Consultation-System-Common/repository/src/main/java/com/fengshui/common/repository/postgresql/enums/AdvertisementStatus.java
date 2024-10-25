package com.fengshui.common.repository.postgresql.enums;

public enum AdvertisementStatus {
    WAITING_APPROVE(0),
    APPROVED(1),
    FINISH(2),
    REJECTED(3);

    private final int type;

    AdvertisementStatus(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static AdvertisementStatus fromType(int type) {
        for (AdvertisementStatus t : AdvertisementStatus.values()) {
            if (t.getType() == type) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid type: " + type);
    }
}
