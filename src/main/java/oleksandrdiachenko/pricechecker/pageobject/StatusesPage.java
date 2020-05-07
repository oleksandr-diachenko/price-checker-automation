package oleksandrdiachenko.pricechecker.pageobject;

import com.codeborne.selenide.SelenideElement;
import oleksandrdiachenko.pricechecker.annotaion.RelativeUrl;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.By.id;

@RelativeUrl("/statuses")
public class StatusesPage extends AbstractPage {

    private final SelenideElement table = $(id("statuses"));

    public List<List<String>> getTable() {
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
        return table.findAll("[mat-cell]").stream()
                .map(SelenideElement::getText)
                .collect(toList());
    }
}