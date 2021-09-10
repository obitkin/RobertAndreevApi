package com.epam.response;

import com.epam.bean.Board;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.http.HttpStatus;

import static org.hamcrest.Matchers.lessThan;

@AllArgsConstructor
public class BoardsResponseHandler {

    @Getter private final Response response;

    public Board getBoard() {
        return response.then().extract().as(Board.class);
    }

    public static ResponseSpecification goodResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(10000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }
}
