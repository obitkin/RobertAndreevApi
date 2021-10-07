package com.epam.services;

import com.epam.bean.Board;
import com.epam.services.requests.builders.ListsRequestBuilder;
import com.epam.services.response.ListsResponse;
import io.restassured.http.Method;
import org.apache.commons.configuration.Configuration;

public class ListsService {

    final Configuration credentials;

    public ListsService(Configuration credentials) {
        this.credentials = credentials;
    }

    private ListsRequestBuilder createRequestWithCredentials() {
        return ListsRequestBuilder
                .build()
                .setKeyAndToken(credentials.getString("key"), credentials.getString("token"));
    }

    public ListsResponse createListOnBoard(String listName, String pos, Board board) {
        return createRequestWithCredentials()
                .setMethod(Method.POST)
                .setName(listName)
                .setPos(pos)
                .setBoardId(board.getId())
                .sendRequest();
    }

    public ListsResponse getListsOnBoard(Board board) {
        return createRequestWithCredentials()
                .setMethod(Method.GET)
                .setBoardId(board.getId())
                .sendRequest();
    }

}
