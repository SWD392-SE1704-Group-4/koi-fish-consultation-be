package com.fengshui.common.repository.postgresql.enums;

public enum AppUserRole {
    USER(0),
    STAFF(1),
    ADMIN(2);
    private final int type;

    AppUserRole(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static AppUserRole fromType(int type) {
        for (AppUserRole t : AppUserRole.values()) {
            if (t.getType() == type) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid type: " + type);
    }
}
