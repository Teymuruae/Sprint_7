package ru.scooter.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import ru.scooter.utils.ConfigProperties;


public class Spec {


    public static RequestSpecification reqSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigProperties.getProperty("baseUri"))
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification respSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

    public static void instalSpec(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }


}
