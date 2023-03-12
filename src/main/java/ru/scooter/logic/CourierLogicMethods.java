package ru.scooter.logic;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import ru.scooter.base.Spec;
import ru.scooter.pojo.CreateCourierPojoRequest;
import ru.scooter.pojo.LoginCourierPojoRequest;
import ru.scooter.utils.ConfigProperties;

public class CourierLogicMethods {

    public ValidatableResponse createrCourier(CreateCourierPojoRequest courierData, int statusCode) {
        Spec.instalSpec(Spec.reqSpec(), Spec.respSpec(statusCode));
        return RestAssured
                .given()
                .body(courierData)
                .when()
                .post(ConfigProperties.getProperty("courierUri"))
                .then();
    }

    public ValidatableResponse loginCourier(LoginCourierPojoRequest loginCourierData, int statusCode) {
        Spec.instalSpec(Spec.reqSpec(), Spec.respSpec(statusCode));
        return RestAssured
                .given()
                .body(loginCourierData)
                .when()
                .post(ConfigProperties.getProperty("courierUri") + ConfigProperties.getProperty("courierLoginUri"))
                .then();
    }

    public ValidatableResponse deleteCourier(int id, int statusCode) {
        Spec.instalSpec(Spec.reqSpec(), Spec.respSpec(statusCode));
        return RestAssured
                .given()
                .when()
                .delete(ConfigProperties.getProperty("courierUri") + id)
                .then();
    }
}
