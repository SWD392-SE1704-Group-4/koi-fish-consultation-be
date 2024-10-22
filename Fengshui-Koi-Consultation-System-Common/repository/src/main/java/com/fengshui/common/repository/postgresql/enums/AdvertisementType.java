package com.fengshui.common.repository.postgresql.enums;

public enum AdvertisementType {
    KOI_FISH(0),
    FENG_SHUI_ITEM(1),
    FISH_POND(2),
    OTHER(3);

    private final int type;

    AdvertisementType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static AdvertisementType fromType(int type) {
        for (AdvertisementType t : AdvertisementType.values()) {
            if (t.getType() == type) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid type: " + type);
    }
}
