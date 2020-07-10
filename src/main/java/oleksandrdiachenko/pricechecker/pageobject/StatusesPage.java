package oleksandrdiachenko.pricechecker.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import oleksandrdiachenko.pricechecker.annotation.RelativeUrl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.By.id;

@RelativeUrl("statuses")
public class StatusesPage extends AbstractPage {

    private final SelenideElement table = $(id("statuses"));
    private final SelenideElement pageNavigationLast = $(".mat-paginator-navigation-last");

    public List<List<String>> getLastTablePage() {
        if (pageNavigationLast.isEnabled()) {
            pageNavigationLast.click();
        }
        List<List<String>> tableData = new ArrayList<>();
        tableData.add(getHead());
        tableData.addAll(getBody());
        return tableData;
    }

    private List<String> getHead() {
        return table.findAll("[mat-header-cell]").stream()
                .map(SelenideElement::getText)
                .collect(toList());
    }

    private List<List<String>> getBody() {
        return table.findAll("[mat-row]").stream()
                .map(this::getRow)
                .collect(toList());
    }

    private List<String> getRow(SelenideElement row) {
        return row.findAll("[mat-cell]").stream()
                .map(SelenideElement::getText)
                .collect(toList());
    }

    @SneakyThrows
    public File download(String time) {
        SelenideElement download = table.findAll("[mat-row]")
                .find(Condition.and("td", Condition.text(time)))
                .find("button");
        return download.download();
    }
}