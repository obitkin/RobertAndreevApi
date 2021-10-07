package com.epam.services.requests.builders;

import com.epam.bean.Board;
import com.epam.constants.Authorization;
import com.epam.services.response.BoardsResponse;
import io.restassured.RestAssured;
import io.restassured.http.Method;

import static com.epam.constants.BoardsParametersName.*;

public class BoardsRequestBuilder extends RequestBuilder {

    public static BoardsRequestBuilder build() {
        BoardsRequestBuilder builder = new BoardsRequestBuilder();
        builder.BASE_PATH = "/1/boards/{id}";
        return builder;
    }

    public BoardsResponse sendRequest() {
        return new BoardsResponse(
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

    public BoardsRequestBuilder setMethod(Method method){
        requestMethod = method;
        return this;
    }

    public BoardsRequestBuilder setKeyAndToken(String key, String token) {
        queryParameters.put(Authorization.KEY, key);
        queryParameters.put(Authorization.TOKEN, token);
        return this;
    }

    public BoardsRequestBuilder setBoard(Board board) {
        if (board.getName() != null) {
            setName(board.getName());
        }
        if (board.getDesc() != null) {
            setDescription(board.getDesc());
        }
        if (board.getClosed() != null) {
            setClosed(board.getClosed());
        }
        if (board.getId() != null) {
            setBoardId(board.getId());
        }
        return this;
    }

    public BoardsRequestBuilder setName(String name) {
        queryParameters.put(NAME, name);
        return this;
    }

    public BoardsRequestBuilder setDescription(String description) {
        queryParameters.put(DESC, description);
        return this;
    }

    public BoardsRequestBuilder setClosed(Boolean closed) {
        queryParameters.put(CLOSED, closed.toString());
        return this;
    }

    public BoardsRequestBuilder setDefaultList(Boolean defaultList) {
        queryParameters.put(DEFAULT_LISTS, defaultList.toString());
        return this;
    }

    public BoardsRequestBuilder setBoardId(String boardId) {
        pathParameters.put(ID, boardId);
        return this;
    }

}