package com.epam.providers;

import com.epam.bean.Board;
import com.epam.bean.TrelloList;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

import java.util.Random;

public class Utils {

    public static final int MAX_LENGTH_NAME = 100;
    public static final int MIN_LENGTH_NAME = 1;
    public static final int MAX_LENGTH_DESC = 101;
    public static final String invalidId = "1";

    public static ResponseSpecification specificationBadId = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
            .expectBody(Matchers.containsString("invalid id"))
            .build();

    static Random random = new Random();

    public static String name() {
        return RandomStringUtils.random(random.nextInt(MAX_LENGTH_NAME) + MIN_LENGTH_NAME,true,true);
    }

    public static String desc() {
        return RandomStringUtils.random(random.nextInt(MAX_LENGTH_DESC),true,true);
    }

    public static Board getBoard() {
        return Board.builder().name(name()).desc(desc()).closed(false).build();
    }

    public static Board getClosedBoard() {
        return Board.builder().name(name()).desc(desc()).closed(true).build();
    }

    public static Board getNotExistBoard() {
        return Board.builder().name(name()).desc(desc()).closed(false).id("1").build();
    }

    public static TrelloList getTrelloList() {
        return TrelloList.builder().name(name()).closed(false).build();
    }

}
