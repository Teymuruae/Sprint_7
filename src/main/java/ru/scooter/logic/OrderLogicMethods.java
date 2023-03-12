package ru.scooter.logic;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import ru.scooter.base.Spec;
import ru.scooter.pojo.CreateOrderPojoRequest;
import ru.scooter.pojo.GetOrderListPojoResponse;
import ru.scooter.utils.ConfigProperties;

import java.util.List;

public class OrderLogicMethods {

    public ValidatableResponse createOrder(CreateOrderPojoRequest orderData, int statusCode){
        Spec.instalSpec(Spec.reqSpec(),Spec.respSpec(statusCode));
        return RestAssured
                .given()
                .body(orderData)
                .when()
                .post(ConfigProperties.getProperty("odrerUri"))
                .then();

    }

    public ValidatableResponse cancelOrder(int tackNumber){
        Spec.instalSpec(Spec.reqSpec(),Spec.respSpec(HttpStatus.SC_OK));
        return RestAssured
                .given().log().all()
                .when().log().all()
                .put(ConfigProperties.getProperty("odrerUri") + ConfigProperties.getProperty("cancelOrderUri"))
                .then().log().all();
    }

    public static List<GetOrderListPojoResponse> getFullOrderList(int statusCode){
       Spec.instalSpec(Spec.reqSpec(), Spec.respSpec(statusCode) );
        return RestAssured
                .given()
                .when()
                .get(ConfigProperties.getProperty("odrerUri"))
                .then().extract().body().jsonPath().getList("orders", GetOrderListPojoResponse.class);
    }
}
