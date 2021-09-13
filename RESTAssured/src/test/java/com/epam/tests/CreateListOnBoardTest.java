package com.epam.tests;

import com.epam.bean.Board;
import com.epam.bean.TrelloList;
import com.epam.matchers.ListsMatcher;
import com.epam.providers.ListsTestProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.epam.services.response.ListsResponse.goodResponseSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CreateListOnBoardTest extends BaseTest {

    @Test(
            dataProvider = "createTrelloList",
            dataProviderClass = ListsTestProvider.class,
            groups = {"RequireCreatedBoard"}
    )
    public void createListsOnBoard(Board board, TrelloList list, String pos) {
        var listOnBoardResponse = listsService.getListsOnBoard(createdBoard);

        listOnBoardResponse.getResponse()
                .then()
                .spec(goodResponseSpecification());
        int listNumberBefore = listOnBoardResponse.getTrelloLists().size();
        List<TrelloList> listsBefore = listOnBoardResponse.getTrelloLists();

        var creationResponse = listsService.createListOnBoard(list.getName(), pos, createdBoard);

        creationResponse.getResponse()
                .then()
                .spec(goodResponseSpecification());
        TrelloList newList = creationResponse.getTrelloList();

        assertThat(creationResponse.getTrelloList(), ListsMatcher.equalIgnoreId(list));

        listOnBoardResponse = listsService.getListsOnBoard(createdBoard);

        listOnBoardResponse.getResponse()
                .then()
                .spec(goodResponseSpecification());
        List<TrelloList> listAfter = listOnBoardResponse.getTrelloLists();

        assertThat(listOnBoardResponse.getTrelloLists().size(), equalTo(listNumberBefore + 1));

        if (pos.equals("bottom")) {
            listsBefore = Stream.concat(listsBefore.stream(), Stream.of(newList)).collect(Collectors.toList());
        } else {
            listsBefore = Stream.concat(Stream.of(newList), listsBefore.stream()).collect(Collectors.toList());
        }
        assertThat(listAfter, equalTo(listsBefore));
    }

    @AfterMethod
    public void deleteBoard() {
        boardsService.deleteBoard(createdBoard);
    }

}
