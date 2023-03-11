package ru.scooter.logic;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import ru.scooter.base.Spec;
import ru.scooter.pojo.CreateCourierPojo;
import ru.scooter.pojo.LoginCourierPojo;
import ru.scooter.utils.ConfigProperties;

public class CourierLogicMethods {

    public ValidatableResponse createrCourier(CreateCourierPojo courierData) {
        Spec.instalSpec(Spec.reqSpec(), Spec.respSpec(HttpStatus.SC_CREATED));
        return RestAssured
                .given()
                .body(courierData)
                .when()
                .post(ConfigProperties.getProperty("courierUri"))
                .then();
    }

    public ValidatableResponse loginCourier(LoginCourierPojo loginCourierData) {
        Spec.instalSpec(Spec.reqSpec(), Spec.respSpec(HttpStatus.SC_OK));
        return RestAssured
                .given()
                .body(loginCourierData)
                .when()
                .post(ConfigProperties.getProperty("courierUri") + ConfigProperties.getProperty("courierLoginUri"))
                .then();
    }

    public ValidatableResponse deleteCourier(int id) {
        Spec.instalSpec(Spec.reqSpec(), Spec.respSpec(HttpStatus.SC_OK));
        return RestAssured
                .given().log().all()
                .when()
                .delete(ConfigProperties.getProperty("courierUri") + id)
                .then();
    }
}
