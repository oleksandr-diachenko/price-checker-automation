package oleksandrdiachenko.pricechecker.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import oleksandrdiachenko.pricechecker.annotation.RelativeUrl;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static oleksandrdiachenko.pricechecker.helper.SelenidePageWrapper.page;
import static org.openqa.selenium.By.id;

@RelativeUrl()
public class MainPage extends AbstractPage {

    private final SelenideElement file = $(id("file"));
    private final SelenideElement fileLabel = $(id("fileInputLabel"));
    private final SelenideElement url = $(id("urlColumn"));
    private final SelenideElement insert = $(id("insertColumn"));
    private final SelenideElement check = $(id("check"));
    private final SelenideElement statuses = $(id("statuses"));
    private final ElementsCollection invalidFeedback = $$(".invalid-feedback");

    public MainPage clickCheck() {
        check.click();
        return page(MainPage.class);
    }

    public boolean isCheckEnabled() {
        return check.isEnabled();
    }

    public int getUrlInputValue() {
        return Integer.parseInt(url.getValue());
    }

    public int getInsertInputValue() {
        return Integer.parseInt(insert.getValue());
    }

    public MainPage selectFile(File file) {
        this.file.uploadFile(file);
        return page(MainPage.class);
    }

    public String getTextFromFileSelector() {
        return fileLabel.getText();
    }

    public Set<FileType> getAcceptableFileTypes() {
        return Arrays.stream(file.getAttribute("accept").split(","))
                .map(FileType::getByExtension)
                .collect(toSet());
    }

    public MainPage setInsertInput(int value) {
        insert.setValue(String.valueOf(value));
        return page(MainPage.class);
    }

    public MainPage clearInsertInput() {
        insert.clear();
        return page(MainPage.class);
    }

    public MainPage setUrlInput(int value) {
        url.setValue(String.valueOf(value));
        return page(MainPage.class);
    }

    public MainPage clearUrlInput() {
        url.clear();
        return page(MainPage.class);
    }

    public StatusesPage clickStatuses() {
        statuses.click();
        return page(StatusesPage.class);
    }

    public List<String> getInvalidFeedback() {
        return invalidFeedback.stream()
                .map(SelenideElement::getText)
                .collect(toList());
    }
}