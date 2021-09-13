package com.epam.services.response;

import com.epam.bean.Board;
import com.epam.bean.TrelloList;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.http.HttpStatus;

import java.util.List;

import static org.hamcrest.Matchers.lessThan;

@AllArgsConstructor
public class ListsResponse {

    @Getter
    private final Response response;

    public List<TrelloList> getTrelloLists() {
        return response.then().extract().body().jsonPath().getList(".", TrelloList.class);
    }

    public TrelloList getTrelloList() {
        return response.then().extract().as(TrelloList.class);
    }

    public static ResponseSpecification goodResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(10000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

}
