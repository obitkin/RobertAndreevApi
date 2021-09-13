package com.epam.tests;

import com.epam.bean.Board;
import com.epam.providers.BoardsTestProvider;
import com.epam.services.response.BoardsResponse;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.epam.matchers.BoardMatcher.equalIgnoreId;
import static com.epam.services.response.BoardsResponse.goodResponseSpecification;
import static org.hamcrest.MatcherAssert.assertThat;

public class CRUDSmokeTests extends BaseTest {

    @Test(
            dataProvider = "CRUDOperationsBoardData",
            dataProviderClass = BoardsTestProvider.class,
            groups = {"RequireCreatedBoard"}
    )
    public void CRUDBoardSmokeTest(Board board, Board updatedBoard) {
        var getResponse = boardsService.getBoard(createdBoard);

        getResponse.getResponse()
                .then()
                .spec(goodResponseSpecification());

        assertThat(getResponse.getBoard(), equalIgnoreId(createdBoard));

        updatedBoard.setId(createdBoard.getId());
        var updateResponse = boardsService.updateBoard(updatedBoard);

        updateResponse.getResponse()
                .then()
                .spec(goodResponseSpecification());

        assertThat(updateResponse.getBoard(), equalIgnoreId(updatedBoard));

        getResponse = boardsService.getBoard(updatedBoard);

        getResponse.getResponse()
                .then()
                .spec(goodResponseSpecification());

        assertThat(getResponse.getBoard(), equalIgnoreId(updatedBoard));

        var deleteResponse = boardsService.deleteBoard(updatedBoard);

        deleteResponse.getResponse()
                .then()
                .spec(goodResponseSpecification());
    }

}
