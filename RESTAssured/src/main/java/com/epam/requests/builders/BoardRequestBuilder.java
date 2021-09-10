package com.epam.requests.builders;

public class BoardRequestBuilder extends BoardsRequestBuilder {

    public static BoardRequestBuilder build() {
        BoardRequestBuilder builder = new BoardRequestBuilder();
        builder.BASE_PATH = "/1/boards/{id}";
        return builder;
    }

    public BoardRequestBuilder setId(String boardId) {
        pathParameters.put("id", boardId);
        return this;
    }

}
