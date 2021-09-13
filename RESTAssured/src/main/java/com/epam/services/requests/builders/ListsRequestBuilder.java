package com.epam.services.requests.builders;

import com.epam.constants.Authorization;
import com.epam.services.response.ListsResponse;
import io.restassured.RestAssured;
import io.restassured.http.Method;

import static com.epam.constants.ListsParametersName.*;

public class ListsRequestBuilder extends RequestBuilder {

    public static ListsRequestBuilder build() {
        ListsRequestBuilder builder = new ListsRequestBuilder();
        builder.BASE_PATH = "/1/boards/{id}/lists";
        return builder;
    }

    public ListsResponse sendRequest() {
        return new ListsResponse(
                RestAssured
                        .given(requestSpecification())
                        .log()
                        .all()
                        .basePath(BASE_PATH)
                        .pathParams(pathParameters)
                        .queryParams(queryParameters)
                        .request(requestMethod)
                        .prettyPeek()
        );
    }

    public ListsRequestBuilder setMethod(Method method){
        requestMethod = method;
        return this;
    }

    public ListsRequestBuilder setKeyAndToken(String key, String token) {
        queryParameters.put(Authorization.KEY, key);
        queryParameters.put(Authorization.TOKEN, token);
        return this;
    }

    public ListsRequestBuilder setName(String name) {
        queryParameters.put(NAME, name);
        return this;
    }

    public ListsRequestBuilder setPos(String pos) {
        queryParameters.put(POS, pos);
        return this;
    }

    public ListsRequestBuilder setFilter(String filter) {
        queryParameters.put(FILTER, filter);
        return this;
    }

    public ListsRequestBuilder setBoardId(String boardId) {
        pathParameters.put(ID, boardId);
        return this;
    }

}
