package com.epam.tests;

import com.epam.bean.Board;
import com.epam.providers.BoardsTestProvider;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.epam.matchers.BoardMatcher.equalIgnoreId;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateBoardTests extends BaseTest {

    protected Board createdBoard;

    public static final int DEFAULT_LISTS_SIZE = 3;

    @Test(
            dataProvider = "createBoardData",
            dataProviderClass = BoardsTestProvider.class
    )
    public void createBoardAndReadListsTest(Board board, ResponseSpecification specification, boolean defaultLists) {
        var creationResponse= boardsService.createBoard(board, defaultLists);

        creationResponse.getResponse()
                .then()
                .spec(specification);

        createdBoard = creationResponse.getBoard();
        assertThat(creationResponse.getBoard(), equalIgnoreId(board));

        var listOnBoardResponse = listsService.getListsOnBoard(createdBoard);
        var matcher = empty();
        if (defaultLists) {
            matcher = hasSize(DEFAULT_LISTS_SIZE);
        }
        assertThat(listOnBoardResponse.getTrelloLists(), matcher);
    }

    @Test(
            dataProvider = "createBoardData",
            dataProviderClass = BoardsTestProvider.class
    )
    public void createBoardWithSameNameTest(Board board, ResponseSpecification specification, boolean defaultLists) {
        var creationResponse= boardsService.createBoard(board, defaultLists);

        creationResponse.getResponse()
                .then()
                .spec(specification);

        createdBoard = creationResponse.getBoard();
        assertThat(creationResponse.getBoard(), equalIgnoreId(board));

        creationResponse = boardsService.createBoard(board, defaultLists);

        creationResponse.getResponse()
                .then()
                .spec(specification);

        Board newBoard = creationResponse.getBoard();

        assertThat(createdBoard.getId(), is(not(equalTo(newBoard.getName()))));
    }

    @AfterMethod
    public void deleteBoard() {
        boardsService.deleteBoard(createdBoard);
    }

}
