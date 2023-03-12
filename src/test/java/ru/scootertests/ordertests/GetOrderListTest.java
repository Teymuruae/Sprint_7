package ru.scootertests.ordertests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import ru.scooter.logic.OrderLogicMethods;
import ru.scooter.pojo.GetOrderListPojoResponse;

import java.util.List;

public class GetOrderListTest {

    @Test
    @DisplayName("get List Order Test")
    @Description("Check that list of orders is not empty")
    public void getlistOrderTest() {
        List<GetOrderListPojoResponse> orderlistResponseData = OrderLogicMethods.getFullOrderList(HttpStatus.SC_OK);
        Assert.assertNotNull(orderlistResponseData);


    }
}
