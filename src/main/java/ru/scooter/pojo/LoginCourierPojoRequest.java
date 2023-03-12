package ru.scooter.pojo;

public class LoginCourierPojoRequest {

    private String login;
    private String password;

    public LoginCourierPojoRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static LoginCourierPojoRequest loginData(CreateCourierPojoRequest courierData) {
        return new LoginCourierPojoRequest(courierData.getLogin(), courierData.getPassword());
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }



    @Override
    public String toString() {
        return "LoginCourierPojoRequest{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
