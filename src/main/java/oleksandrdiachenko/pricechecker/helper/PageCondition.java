package oleksandrdiachenko.pricechecker.helper;

import oleksandrdiachenko.pricechecker.pageobject.StatusesPage;

import java.util.List;

public class PageCondition {

    private PageCondition() {
        throw new UnsupportedOperationException("Object creation is not allowed");
    }

    public static ExpectPageCondition<StatusesPage, Boolean> lastRowContainsText(String text) {
        return statusesPage -> {
            List<List<String>> table = statusesPage.getTable();
            return table.get(table.size() - 1).contains(text);
        };
    }
}
