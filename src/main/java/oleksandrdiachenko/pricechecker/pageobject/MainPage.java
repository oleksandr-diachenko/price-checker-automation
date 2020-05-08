package oleksandrdiachenko.pricechecker.pageobject;

import com.codeborne.selenide.SelenideElement;
import oleksandrdiachenko.pricechecker.annotaion.RelativeUrl;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static oleksandrdiachenko.pricechecker.util.SelenidePageWrapper.page;
import static org.openqa.selenium.By.id;

@RelativeUrl()
public class MainPage extends AbstractPage {

    private final SelenideElement fileChooser = $(id("file"));
    private final SelenideElement url = $(id("urlColumn"));
    private final SelenideElement insert = $(id("insertColumn"));
    private final SelenideElement check = $(id("check"));
    private final SelenideElement statuses = $(id("statuses"));

    public boolean isCheckEnabled() {
        return check.isEnabled();
    }

    public int getUrlInputValue() {
        return Integer.parseInt(url.getValue());
    }

    public int getInsertInputValue() {
        return Integer.parseInt(insert.getValue());
    }

    public MainPage chooseFile(File file) {
        fileChooser.uploadFile(file);
        return page(MainPage.class);
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
}