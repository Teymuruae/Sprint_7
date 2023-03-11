package ru.scooter.pojo;

public class LoginCourierPojo {

    private String login;
    private String password;

    public LoginCourierPojo(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static LoginCourierPojo loginData(CreateCourierPojo courierData) {
        return new LoginCourierPojo(courierData.getLogin(), courierData.getPassword());
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
