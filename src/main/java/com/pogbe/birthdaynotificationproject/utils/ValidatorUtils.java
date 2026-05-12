package com.pogbe.birthdaynotificationproject.utils;

public class ValidatorUtils {
    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }

        if (!phoneNumber.startsWith("+234")) {
            return false;
        }

        if (phoneNumber.length() != 14) {
            return false;
        }
        return isNumeric(phoneNumber.substring(1));
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) return false;
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
