package ru.scootertests.couriertests;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.scooter.logic.CourierLogicMethods;
import ru.scooter.pojo.CreateCourierPojoRequest;
import ru.scooter.pojo.LoginCourierPojoRequest;
import ru.scooter.pojo.randomgenerator.RandomCourierGenerator;
import ru.scooter.utils.ErrorMessages;


public class CourierLoginWithWrongFieldTest {

    private CourierLogicMethods courierLogicMethods;
    private int id;
    CreateCourierPojoRequest courierData;
    LoginCourierPojoRequest loginCourierDataTrue;
    ValidatableResponse loginCourierTrue;

    @Before
    public void setUp() {
        courierLogicMethods = new CourierLogicMethods();
    }

    @After
    public void clear() {
        courierLogicMethods.deleteCourier(id, HttpStatus.SC_OK);
    }

    public void createAndLogin() {
        courierData = RandomCourierGenerator.createRandomCourier();
        courierLogicMethods.createrCourier(courierData, HttpStatus.SC_CREATED);

        //для удаления из БД
        loginCourierDataTrue = LoginCourierPojoRequest.loginData(courierData);
        loginCourierTrue = courierLogicMethods.loginCourier(loginCourierDataTrue, HttpStatus.SC_OK);
        id = loginCourierTrue.extract().path("id");
    }

    @Test
    public void loginCourierWithWrongPasswordFieldTest() {
        createAndLogin();

        LoginCourierPojoRequest loginCourierDataFalse = new LoginCourierPojoRequest(courierData.getLogin(),
                RandomCourierGenerator.generateString());
        ValidatableResponse loginCourierFalse = courierLogicMethods.loginCourier(loginCourierDataFalse,
                HttpStatus.SC_NOT_FOUND);
        String actualErrorMessage = loginCourierFalse.extract().path("message");

        Assert.assertEquals(ErrorMessages.getLOGIN_COURIER_404(), actualErrorMessage);
    }

    @Test
    public void loginCourierWithWrongLoginFieldTest() {
        createAndLogin();

        LoginCourierPojoRequest loginCourierData = new LoginCourierPojoRequest(RandomCourierGenerator.generateString(),
                courierData.getPassword());
        ValidatableResponse loginCourier = courierLogicMethods.loginCourier(loginCourierData, HttpStatus.SC_NOT_FOUND);
        String actualErrorMessage = loginCourier.extract().path("message");
        Assert.assertEquals(ErrorMessages.getLOGIN_COURIER_404(), actualErrorMessage);
    }


}
