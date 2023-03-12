package ru.scootertests.ordertests;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import ru.scooter.logic.OrderLogicMethods;
import ru.scooter.pojo.GetOrderListPojoResponse;

import java.util.List;

public class GetOrderListTest {

    @Test
    public void getlistOrderTest() {
        List<GetOrderListPojoResponse> orderlistResponseData = OrderLogicMethods.getFullOrderList(HttpStatus.SC_OK);
        Assert.assertNotNull(orderlistResponseData);


    }
}
