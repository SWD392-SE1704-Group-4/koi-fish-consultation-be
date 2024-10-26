package com.fengshui.common.repository.postgresql.enums;

public enum GenderEnum {
    MALE(0),
    FEMALE(1),
    UN_SPECIFIED(2);

    private final int type;

    GenderEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static GenderEnum fromType(int type) {
        for (GenderEnum t : GenderEnum.values()) {
            if (t.getType() == type) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid type: " + type);
    }
}
