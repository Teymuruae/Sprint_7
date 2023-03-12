package ru.scootertests.couriertests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.scooter.logic.CourierLogicMethods;
import ru.scooter.pojo.CreateCourierPojoRequest;
import ru.scooter.pojo.randomgenerator.RandomCourierGenerator;
import ru.scooter.utils.ErrorMessages;

@RunWith(Parameterized.class)
public class CreateCourierWithourOneFieldTest {

    private String login;
    private String password;
    private String firstName;
    private int statusCode = HttpStatus.SC_BAD_REQUEST;
    private String expectedErrorMessage = ErrorMessages.getCREATE_COURIER_400();

    public CreateCourierWithourOneFieldTest(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;

    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        return new Object[][]{
                {null, RandomCourierGenerator.generateString(), RandomCourierGenerator.generateString() },
                {RandomCourierGenerator.generateString(), null, RandomCourierGenerator.generateString()},
                {null, null, RandomCourierGenerator.generateString()},
                {RandomCourierGenerator.generateString(), null, null},
                {null, RandomCourierGenerator.generateString(), null
                        }


        };
    }

    @Test
    @DisplayName("create Courier Without One Field Test")
    public void createCourierWithoutOneFieldTest() {
        CourierLogicMethods courierLogicMethods = new CourierLogicMethods();
        CreateCourierPojoRequest courierWithoutOneFieldData = new CreateCourierPojoRequest(login, password, firstName);
        ValidatableResponse newCourierWithoutOneField = courierLogicMethods
                .createrCourier(courierWithoutOneFieldData, statusCode);
        String actualErrorMessage = newCourierWithoutOneField.extract().path("message");
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);


    }
}
