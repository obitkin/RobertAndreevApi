package com.epam.services;

import com.epam.bean.Board;
import com.epam.services.requests.builders.BoardsRequestBuilder;
import com.epam.services.response.BoardsResponse;
import io.restassured.http.Method;
import org.apache.commons.configuration.Configuration;

public class BoardsService {

    final Configuration credentials;

    public BoardsService(Configuration credentials) {
        this.credentials = credentials;
    }

    private BoardsRequestBuilder createRequestWithCredentials() {
        return BoardsRequestBuilder
                .build()
                .setBoardId("")
                .setKeyAndToken(credentials.getString("key"), credentials.getString("token"));
    }

    public BoardsResponse createBoard(Board board) {
        return createBoard(board, true);
    }

    public BoardsResponse createBoard(Board board, Boolean defaultList) {
        return createRequestWithCredentials()
                .setMethod(Method.POST)
                .setName(board.getName())
                .setDefaultList(defaultList)
                .setDescription(board.getDesc())
                .sendRequest();
    }

    public BoardsResponse getBoard(Board board) {
        return createRequestWithCredentials()
                .setMethod(Method.GET)
                .setBoardId(board.getId())
                .sendRequest();
    }

    public BoardsResponse deleteBoard(Board board) {
        return createRequestWithCredentials()
                .setMethod(Method.DELETE)
                .setBoardId(board.getId())
                .sendRequest();
    }

    public BoardsResponse updateBoard(Board board) {
        return createRequestWithCredentials()
                .setMethod(Method.PUT)
                .setBoard(board)
                .sendRequest();
    }

}
