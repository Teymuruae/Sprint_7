package ru.scooter.pojo.randomgenerator;

import org.apache.commons.lang3.RandomStringUtils;
import ru.scooter.pojo.CreateCourierPojoRequest;


public class RandomCourierGenerator {

    public static CreateCourierPojoRequest createRandomCourier(){
        String login = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic( 10);
        return new CreateCourierPojoRequest(login, password, firstName);
    }

    public static String generateString(){
        return RandomStringUtils.randomAlphabetic(10);
    }
}
