package ru.scooter.pojo.randomgenerator;

import org.apache.commons.lang3.RandomStringUtils;
import ru.scooter.pojo.CreateCourierPojo;


public class RandomCourierGenerator {

    public static CreateCourierPojo createRandomCourier(){
        String login = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic( 10);
        return new CreateCourierPojo(login, password, firstName);
    }
}
