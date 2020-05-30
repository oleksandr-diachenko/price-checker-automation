package oleksandrdiachenko.pricechecker.pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AlertContainer {

    private final SelenideElement snackBar = $("simple-snack-bar");

    public String getMessage() {
        return snackBar.find("span").text();
    }
}
