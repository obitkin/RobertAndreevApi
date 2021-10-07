package com.epam.tests;

import com.epam.bean.Board;
import com.epam.services.BoardsService;
import com.epam.services.ListsService;
import com.epam.services.response.BoardsResponse;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.epam.matchers.BoardMatcher.equalIgnoreId;
import static org.hamcrest.MatcherAssert.assertThat;

public class BaseTest {

    protected BoardsService boardsService;
    protected ListsService listsService;
    protected Configuration credentials;

    @BeforeClass
    public void setUp() {
        try {
            credentials = new PropertiesConfiguration("src/test/resources/credential.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        boardsService = new BoardsService(credentials);
        listsService = new ListsService(credentials);
    }

    protected Board createdBoard;

    @BeforeMethod(onlyForGroups = {"RequireCreatedBoard"})
    public void createBoard(Object[] args) {
        Board board = (Board) args[0];
        var creationResponse= boardsService.createBoard(board);

        creationResponse.getResponse()
                .then()
                .spec(BoardsResponse.goodResponseSpecification());

        createdBoard = creationResponse.getBoard();
        assertThat(createdBoard, equalIgnoreId(board));
    }

}
