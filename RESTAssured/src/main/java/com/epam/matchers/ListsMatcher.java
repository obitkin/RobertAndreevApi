package com.epam.matchers;

import com.epam.bean.TrelloList;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ListsMatcher extends TypeSafeMatcher<TrelloList> {

    private TrelloList expectedList;

    public ListsMatcher(TrelloList expectedList) {
        this.expectedList = expectedList;
    }

    @Override
    protected boolean matchesSafely(TrelloList list) {
        return expectedList.getClosed() == list.getClosed() &&
                expectedList.getName().equals(list.getName());
    }

    @Override
    public void describeTo(Description description) {}

    public static Matcher<TrelloList> equalIgnoreId(TrelloList expectedList) {
        return new ListsMatcher(expectedList);
    }

}