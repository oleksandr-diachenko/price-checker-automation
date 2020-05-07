package oleksandrdiachenko.pricechecker.pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class FooterContainer {

    private final SelenideElement copyright = $(".footer-copyright");

    public String getCopyright() {
        return copyright.getText();
    }

    public String getCopyrightLink() {
        return copyright.getAttribute("href");
    }
}
