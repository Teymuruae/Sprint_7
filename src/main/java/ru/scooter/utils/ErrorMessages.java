package ru.scooter.utils;

public class ErrorMessages {
    private static final String CREATE_COURIER_400 = "Недостаточно данных для создания учетной записи";
    private static final String CREATE_COURIER_409 = "Этот логин уже используется";
    private static final String LOGIN_COURIER_400 = "Недостаточно данных для входа";
    private static final String LOGIN_COURIER_404 = "Учетная запись не найдена";

    public static String getCREATE_COURIER_400() {
        return CREATE_COURIER_400;
    }

    public static String getCREATE_COURIER_409() {
        return CREATE_COURIER_409;
    }

    public static String getLOGIN_COURIER_400() {
        return LOGIN_COURIER_400;
    }

    public static String getLOGIN_COURIER_404() {
        return LOGIN_COURIER_404;
    }
}
