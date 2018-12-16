package com.maksymmykytiuk.elephants.constant;

public class UserType {
    private static final Long STUDENT = 1L;
    private static final Long LECTURER = 2L;

    public static Long byRoleId(Long id) {
        switch (id.intValue()) {
            case 1:
                return STUDENT;
            case 3:
                return LECTURER;
            default:
                return 0L;
        }
    }
}
