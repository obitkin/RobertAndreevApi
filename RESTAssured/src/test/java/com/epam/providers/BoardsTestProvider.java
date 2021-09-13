package com.epam.providers;

import org.testng.annotations.DataProvider;

import static com.epam.providers.Utils.*;
import static com.epam.services.response.BoardsResponse.goodResponseSpecification;

public class BoardsTestProvider {

    @DataProvider
    public Object[][] createBoardData() {
        return new Object[][]{
                {getBoard(), goodResponseSpecification(), true},
                {getBoard(), goodResponseSpecification(), true},
                {getBoard(), goodResponseSpecification(), false},
                {getBoard(), goodResponseSpecification(), false}
        };
    }

    @DataProvider
    public Object[][] deleteBoardData() {
        return new Object[][]{
                {getBoard(), goodResponseSpecification()},
                {getBoard(), goodResponseSpecification()}
        };
    }

    @DataProvider
    public Object[][] deleteNotExistBoardData() {
        return new Object[][]{
                {getNotExistBoard(), specificationBadId}
        };
    }

    @DataProvider
    public Object[][] readBoardData() {
        return deleteBoardData();
    }

    @DataProvider
    public Object[][] readNotExistBoardData() {
        return deleteNotExistBoardData();
    }

    @DataProvider
    public Object[][] updateExistBoardData() {
        return new Object[][]{
                {getBoard(), getBoard(), goodResponseSpecification()},
                {getBoard(), getClosedBoard(), goodResponseSpecification()},
        };
    }

    @DataProvider
    public Object[][] updateNotExistBoardData() {
        return deleteNotExistBoardData();
    }

    @DataProvider
    public Object[][] CRUDOperationsBoardData() {
        return new Object[][]{
                {getBoard(), getBoard()},
                {getBoard(), getClosedBoard()},
        };
    }

}
