package com.maksymmykytiuk.elephants.constant;

public enum RoleEnum {
    STUDENT_ROLE(1L),
    ADMIN_ROLE(2L),
    LECTURER_ROLE(3L);

    private Long id;

    RoleEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
