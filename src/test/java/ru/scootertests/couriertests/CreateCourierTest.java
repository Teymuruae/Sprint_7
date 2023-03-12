package ru.scootertests.couriertests;

import io.qameta.allure.junit4.DisplayName;
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

public class CreateCourierTest {

    private int id;
    private CourierLogicMethods courierLogicMethods;

    @Before
    public void setUp() {
        courierLogicMethods = new CourierLogicMethods();
    }

    @After
    public void clear() {
        courierLogicMethods.deleteCourier(id, HttpStatus.SC_OK);
    }

    //проверка создания курьера
    @Test
    @DisplayName("create Courier Test")
    public void createCourierTest() {
        CreateCourierPojoRequest courierData = RandomCourierGenerator.createRandomCourier();
        ValidatableResponse newCourier = courierLogicMethods.createrCourier(courierData, HttpStatus.SC_CREATED);
        boolean isCourierCreated = newCourier.extract().path("ok");
        Assert.assertTrue(isCourierCreated);

        LoginCourierPojoRequest loginData = LoginCourierPojoRequest.loginData(courierData);
        ValidatableResponse loginCourier = courierLogicMethods.loginCourier(loginData, HttpStatus.SC_OK);
        id = loginCourier.extract().path("id");
        Assert.assertNotNull(id);

    }

    //проверка невозможности создания одинакового курьера
    @Test
    @DisplayName("create Duplicate Courier Conflict Test")
    public void createDuplicateCourierConflictTest() {
        createCourierTest();

        CreateCourierPojoRequest courierData = RandomCourierGenerator.createRandomCourier();
        ValidatableResponse newCourier = courierLogicMethods.createrCourier(courierData, HttpStatus.SC_CREATED);
        boolean isCourierCreated = newCourier.extract().path("ok");
        Assert.assertTrue(isCourierCreated);

        ValidatableResponse newCourier2 = courierLogicMethods.createrCourier(courierData, HttpStatus.SC_CONFLICT);
        String actualErrorMessage = newCourier2.extract().path("message");
        Assert.assertEquals(ErrorMessages.getCREATE_COURIER_409(), actualErrorMessage);

        LoginCourierPojoRequest loginData = LoginCourierPojoRequest.loginData(courierData);
        ValidatableResponse loginCourier = courierLogicMethods.loginCourier(loginData, HttpStatus.SC_OK);
        id = loginCourier.extract().path("id");
        Assert.assertNotNull(id);

    }
}
