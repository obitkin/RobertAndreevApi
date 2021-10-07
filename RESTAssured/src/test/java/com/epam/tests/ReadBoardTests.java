package com.epam.tests;

import com.epam.bean.Board;
import com.epam.providers.BoardsTestProvider;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.epam.matchers.BoardMatcher.equalIgnoreId;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReadBoardTests extends BaseTest {

    @Test(
            dataProvider = "readBoardData",
            dataProviderClass = BoardsTestProvider.class,
            groups = {"RequireCreatedBoard"}
    )
    public void readExistBoardTest(Board board, ResponseSpecification specification) {
        var readingResponse = boardsService.getBoard(createdBoard);

        readingResponse.getResponse()
                .then()
                .spec(specification);

        assertThat(readingResponse.getBoard(), equalIgnoreId(createdBoard));
    }

    @Test(
            dataProvider = "readNotExistBoardData",
            dataProviderClass = BoardsTestProvider.class
    )
    public void readNotExistBoardTest(Board board, ResponseSpecification specification) {
        var readingResponse = boardsService.getBoard(board);

        readingResponse.getResponse()
                .then()
                .spec(specification);
    }

    @AfterMethod(onlyForGroups = {"RequireCreatedBoard"})
    public void deleteBoard() {
        boardsService.deleteBoard(createdBoard);
    }

}
