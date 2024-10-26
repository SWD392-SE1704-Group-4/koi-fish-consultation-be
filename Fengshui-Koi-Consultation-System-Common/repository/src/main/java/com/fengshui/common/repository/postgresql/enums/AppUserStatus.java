package com.fengshui.common.repository.postgresql.enums;

public enum AppUserStatus {
    ACTIVE(1),
    UN_ACTIVE(0);
    private final int type;

    AppUserStatus(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static AppUserStatus fromType(int type) {
        for (AppUserStatus t : AppUserStatus.values()) {
            if (t.getType() == type) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid type: " + type);
    }
}
