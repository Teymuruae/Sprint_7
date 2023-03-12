package ru.scooter.pojo;

import java.util.List;

public class GetOrderListPojoResponse {

    public Integer id;
    public Integer courierId;
    public String firstName;
    public String lastName;
    public String address;
    public String metroStation;
    public String phone;
    public Integer rentTime;
    public String deliveryDate;
    public Integer track;
    public List<String> color;
    public String comment;
    public String createdAt;
    public String updatedAt;
    public Integer status;

    public GetOrderListPojoResponse(Integer id, Integer courierId, String firstName, String lastName, String address,
                                    String metroStation, String phone, Integer rentTime, String deliveryDate,
                                    Integer track, List<String> color, String comment, String createdAt,
                                    String updatedAt, Integer status) {
        this.id = id;
        this.courierId = courierId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.track = track;
        this.color = color;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
}
