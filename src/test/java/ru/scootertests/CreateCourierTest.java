package ru.scootertests;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.scooter.logic.CourierLogicMethods;
import ru.scooter.pojo.CreateCourierPojo;
import ru.scooter.pojo.LoginCourierPojo;
import ru.scooter.pojo.randomgenerator.RandomCourierGenerator;

public class CreateCourierTest {

    int id;
    CourierLogicMethods courierLogicMethods;

    @Before
    public void setUp(){
        courierLogicMethods = new CourierLogicMethods();
    }

    @After
    public void clear(){
        courierLogicMethods.deleteCourier(id);
    }

    @Test
    public void CreateCourierTest(){
        CreateCourierPojo courierData = RandomCourierGenerator.createRandomCourier();
        ValidatableResponse newCourier = courierLogicMethods.createrCourier(courierData);
        boolean isCourierCreated = newCourier.extract().path("ok");
        Assert.assertTrue(isCourierCreated);

        LoginCourierPojo loginData = LoginCourierPojo.loginData(courierData);
        ValidatableResponse loginCourier =  courierLogicMethods.loginCourier(loginData);
        id = loginCourier.extract().path("id");
        Assert.assertNotNull(id);

    }
}
