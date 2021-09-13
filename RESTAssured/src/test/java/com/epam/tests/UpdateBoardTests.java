package com.epam.tests;

import com.epam.bean.Board;
import com.epam.providers.BoardsTestProvider;
import com.epam.services.response.BoardsResponse;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.matchers.BoardMatcher.equalIgnoreId;
import static org.hamcrest.MatcherAssert.assertThat;

public class UpdateBoardTests extends BaseTest {

    @Test(
            dataProvider = "updateExistBoardData",
            dataProviderClass = BoardsTestProvider.class,
            groups = {"RequireCreatedBoard"}
    )
    public void updateExistBoardTest(Board board, Board updatedBoard, ResponseSpecification specification) {
        updatedBoard.setId(createdBoard.getId());
        var updateResponse = boardsService.updateBoard(updatedBoard);

        updateResponse.getResponse()
                .then()
                .spec(specification);

        assertThat(updateResponse.getBoard(), equalIgnoreId(updatedBoard));
    }

    @Test(
            dataProvider = "updateNotExistBoardData",
            dataProviderClass = BoardsTestProvider.class
    )
    public void updateNotExistBoardTest(Board board, ResponseSpecification specification) {
        var updateResponse = boardsService.updateBoard(board);

        updateResponse.getResponse()
                .then()
                .spec(specification);
    }

    @AfterMethod
    public void deleteBoard() {
        boardsService.deleteBoard(createdBoard);
    }

}
