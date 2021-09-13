package com.epam.providers;

import org.testng.annotations.DataProvider;

import static com.epam.providers.Utils.*;

public class ListsTestProvider {

    @DataProvider
    public Object[][] createTrelloList() {
        return new Object[][]{
                {getBoard(), getTrelloList(), "top"},
                {getBoard(), getTrelloList(), "bottom"}
        };
    }

}
