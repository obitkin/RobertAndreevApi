package com.epam.matchers;

import com.epam.bean.Board;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class BoardMatcher extends TypeSafeMatcher<Board> {

    private Board expectedBoard;

    public BoardMatcher(Board expectedBoard) {
        this.expectedBoard = expectedBoard;
    }

    @Override
    protected boolean matchesSafely(Board board) {
        return expectedBoard.getClosed() == board.getClosed() &&
                expectedBoard.getName().equals(board.getName()) &&
                expectedBoard.getDesc().equals(board.getDesc());
    }

    @Override
    public void describeTo(Description description) {}

    public static Matcher<Board> equalIgnoreId(Board expectedBoard) {
        return new BoardMatcher(expectedBoard);
    }

}
