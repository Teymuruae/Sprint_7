package ru.scooter.pojo;

public class CreateCourierPojo {

    private String login;
    private String password;
    private String firstName;

    public CreateCourierPojo(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }
}
