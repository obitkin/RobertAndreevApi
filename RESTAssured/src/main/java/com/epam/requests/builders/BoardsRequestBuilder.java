package com.epam.requests.builders;

import com.epam.constants.Authorization;
import com.epam.constants.BoardsParametersName;
import com.epam.response.BoardsResponseHandler;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class BoardsRequestBuilder extends RequestBuilder {

    public static BoardsRequestBuilder build() {
        BoardsRequestBuilder builder = new BoardsRequestBuilder();
        builder.BASE_PATH = "/1/boards/";
        return builder;
    }

    public BoardsRequestBuilder setMethod(Method method){
        requestMethod = method;
        return this;
    }

    public BoardsRequestBuilder setKeyAndToken(String key, String token) {
        queryParameters.put(Authorization.KEY, key);
        queryParameters.put(Authorization.TOKEN, token);
        return this;
    }

    public BoardsResponseHandler sendRequest() {
        return new BoardsResponseHandler(
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

    public BoardsRequestBuilder setName(String name) {
        queryParameters.put(BoardsParametersName.NAME, name);
        return this;
    }

    public BoardsRequestBuilder setDescription(String description) {
        queryParameters.put(BoardsParametersName.DESC, description);
        return this;
    }

}