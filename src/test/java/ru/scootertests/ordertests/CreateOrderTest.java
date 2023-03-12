package ru.scootertests.ordertests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.scooter.logic.OrderLogicMethods;
import ru.scooter.pojo.CreateOrderPojoRequest;
import java.util.List;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private OrderLogicMethods orderLogicMethods;
    private int track;

    private String firstName;
    private String lastName;
    private String address;
    private Integer metroStation;
    private String phone;
    private Integer rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;


    public CreateOrderTest(String firstName, String lastName, String address, Integer metroStation, String phone,
                           Integer rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        return new Object[][]{
                {"Ivan", "Ivanov", "Pushkinskaya, 3", 4, "+7 800 355 35 35", 5, "2023-03-16", "some comment",
                        List.of("BLACK")},
                {"Ivan", "Ivanov", "Pushkinskaya, 3", 4, "+7 800 355 35 35", 5, "2023-03-16", "some comment",
                        List.of("GREY")},
                {"Ivan", "Ivanov", "Pushkinskaya, 3", 4, "+7 800 355 35 35", 5, "2023-03-16", "some comment",
                        List.of("BLACK", "GREY")},
                {"Ivan", "Ivanov", "Pushkinskaya, 3", 4, "+7 800 355 35 35", 5, "2023-03-16", "some comment",
                        null}
        };
    }

    @Before
    public void setUp() {
        orderLogicMethods = new OrderLogicMethods();
    }

    @Test
    @DisplayName("create Order Test")
    public void createOrderTest() {
        CreateOrderPojoRequest orderData = new CreateOrderPojoRequest(firstName, lastName, address, metroStation, phone, rentTime,
                deliveryDate, comment, color);
        ValidatableResponse newOrder = orderLogicMethods.createOrder(orderData, HttpStatus.SC_CREATED);
        track = newOrder.extract().path("track");
        Assert.assertNotNull(track);

    }

    @After
    public void clearOrder() {
        orderLogicMethods.cancelOrder(track);

    }
}
