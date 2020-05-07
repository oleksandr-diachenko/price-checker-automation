package oleksandrdiachenko.pricechecker.pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class HeaderContainer {

    private final SelenideElement facebook = $(".fa-facebook");
    private final SelenideElement linkedIn = $(".fa-linkedin");
    private final SelenideElement github = $(".fa-github");
    private final SelenideElement telegram = $(".fa-telegram");
    private final SelenideElement email = $(".fa-at");
}
