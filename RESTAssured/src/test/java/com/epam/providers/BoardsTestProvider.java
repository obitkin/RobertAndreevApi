package com.epam.providers;

import com.epam.bean.Board;
import org.testng.annotations.DataProvider;

public class BoardsTestProvider {

    @DataProvider
    public Object[][] createBoardData() {
        return new Object[][]{
                {new Board(null, "name", "desc", false)}
        };
    }

}
