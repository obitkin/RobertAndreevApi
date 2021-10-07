package com.epam.tests;

import com.epam.bean.Board;
import com.epam.providers.BoardsTestProvider;

import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

public class DeleteBoardTests extends BaseTest {

    @Test(
            dataProvider = "deleteBoardData",
            dataProviderClass = BoardsTestProvider.class,
            groups = {"RequireCreatedBoard"}
    )
    public void deleteExistBoardTest(Board board, ResponseSpecification specification) {
        var deletionResponse = boardsService.deleteBoard(createdBoard);

        deletionResponse.getResponse()
                .then()
                .spec(specification);
    }

    @Test(
            dataProvider = "deleteNotExistBoardData",
            dataProviderClass = BoardsTestProvider.class
    )
    public void deleteNotExistBoardTest(Board board, ResponseSpecification specification) {
        var deletionResponse = boardsService.deleteBoard(board);

        deletionResponse.getResponse()
                .then()
                .spec(specification);
    }

}
