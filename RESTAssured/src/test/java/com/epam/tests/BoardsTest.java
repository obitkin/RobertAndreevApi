package com.epam.tests;

import com.epam.bean.Board;
import com.epam.providers.BoardsTestProvider;
import com.epam.requests.builders.BoardRequestBuilder;
import com.epam.requests.builders.BoardsRequestBuilder;
import com.epam.response.BoardsResponseHandler;
import com.epam.steps.ActionStep;
import io.restassured.http.Method;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class BoardsTest {

    static ActionStep actionStep;
    static Configuration credentials;

    static {
        try {
            credentials = new PropertiesConfiguration("src/test/resources/credential.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        actionStep = new ActionStep(credentials);
    }

    Board board;

    @Test(dataProvider = "createBoardData",
            dataProviderClass = BoardsTestProvider.class)
    public void createBoardTest(Board board) {
        var boardsResponseHandlerPost = actionStep.createBoard(board);

        boardsResponseHandlerPost.getResponse()
                .then()
                .assertThat()
                .spec(BoardsResponseHandler.goodResponseSpecification());

        assertThat(boardsResponseHandlerPost.getBoard(), equalTo(board));

        var boardsResponseHandlerGet = actionStep.getBoard(boardsResponseHandlerPost.getBoard().getId());

        boardsResponseHandlerGet.getResponse()
                .then()
                .assertThat()
                .spec(BoardsResponseHandler.goodResponseSpecification());

        assertThat(boardsResponseHandlerGet.getBoard(), equalTo(boardsResponseHandlerGet.getBoard()));
    }

    @BeforeMethod(onlyForGroups = "handle board")
    public void createDefaultBoard() {
        this.board = actionStep.createBoard(new Board(null, "BOARD", "DESC", false)).getBoard();
    }

    @Test(dataProvider = "createBoardData",
            dataProviderClass = BoardsTestProvider.class,
            groups = {"handle board"})
    public void deleteBoardTest() {

        var boardsResponseHandlerDel = actionStep.deleteBoard(board.getId());

        boardsResponseHandlerDel.getResponse()
                .then()
                .assertThat()
                .spec(BoardsResponseHandler.goodResponseSpecification());

        var boardsResponseHandlerGet = actionStep.getBoard(board.getId());

        boardsResponseHandlerGet.getResponse()
                .then()
                .assertThat()
                .statusCode(404);
    }

}
