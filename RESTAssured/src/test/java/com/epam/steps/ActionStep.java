package com.epam.steps;

import com.epam.bean.Board;
import com.epam.requests.builders.BoardRequestBuilder;
import com.epam.requests.builders.BoardsRequestBuilder;
import com.epam.response.BoardsResponseHandler;
import io.restassured.http.Method;
import org.apache.commons.configuration.Configuration;

public class ActionStep {

    final Configuration credentials;

    public ActionStep(Configuration credentials) {
        this.credentials = credentials;
    }

    public BoardsResponseHandler createBoard(Board board) {
        return BoardsRequestBuilder
                .build()
                .setKeyAndToken(credentials.getString("key"), credentials.getString("token"))
                .setMethod(Method.POST)
                .setName(board.getName())
                .setDescription(board.getDesc())
                .sendRequest();
    }

    public BoardsResponseHandler getBoard(String id) {
        return BoardRequestBuilder
                .build()
                .setId(id)
                .setKeyAndToken(credentials.getString("key"), credentials.getString("token"))
                .setMethod(Method.GET)
                .sendRequest();
    }

    public BoardsResponseHandler deleteBoard(String id) {
        return BoardRequestBuilder
                .build()
                .setId(id)
                .setKeyAndToken(credentials.getString("key"), credentials.getString("token"))
                .setMethod(Method.DELETE)
                .sendRequest();
    }

    public BoardsResponseHandler updateBoard(Board board, String id) {
        return BoardRequestBuilder
                .build()
                .setId(id)
                .setKeyAndToken(credentials.getString("key"), credentials.getString("token"))
                .setMethod(Method.PUT)
                .setName(board.getName())
                .setDescription(board.getDesc())
                .sendRequest();
    }

}
