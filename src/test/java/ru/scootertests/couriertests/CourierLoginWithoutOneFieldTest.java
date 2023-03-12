package ru.scootertests.couriertests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.scooter.logic.CourierLogicMethods;
import ru.scooter.pojo.CreateCourierPojoRequest;
import ru.scooter.pojo.LoginCourierPojoRequest;
import ru.scooter.pojo.randomgenerator.RandomCourierGenerator;
import ru.scooter.utils.ErrorMessages;

@RunWith(Parameterized.class)
public class CourierLoginWithoutOneFieldTest {

    private CourierLogicMethods courierLogicMethods;
    private int id;
    private static CreateCourierPojoRequest courierData;
    private String login;
    private String password;
    private int expectedStatusCode = HttpStatus.SC_BAD_REQUEST;
    private final String expectedErrorMessage = ErrorMessages.getLOGIN_COURIER_400();

    public CourierLoginWithoutOneFieldTest(String login, String password) {
        this.login = login;
        this.password = password;

    }

    @Before
    public void setUp() {
        courierLogicMethods = new CourierLogicMethods();
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        return new Object[][]{
                {"", RandomCourierGenerator.generateString()},
                {RandomCourierGenerator.generateString(), ""},
                {"", ""},
                {null, RandomCourierGenerator.generateString()},
                {RandomCourierGenerator.generateString(), null},
                {null, null}
        };
    }

    //проверка, что логин выдал ошибку из-за отсутствующего необходимого поля
    @Test
    @DisplayName("courier Login Fail Test")
    public void courierLoginFailTest() {
        CreateCourierPojoRequest courierData = RandomCourierGenerator.createRandomCourier();
        courierLogicMethods.createrCourier(courierData, HttpStatus.SC_CREATED);

        LoginCourierPojoRequest loginCourierData = new LoginCourierPojoRequest(login, password);
        ValidatableResponse courierLogin = courierLogicMethods.loginCourier(loginCourierData, expectedStatusCode);
        String actualErrorMessage = courierLogin.extract().path("message");
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

}
