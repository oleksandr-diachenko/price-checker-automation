package oleksandrdiachenko.pricechecker.pageobject;

import static com.codeborne.selenide.Selenide.page;

public abstract class AbstractPage {

    protected HeaderContainer header() {
        return page(HeaderContainer.class);
    }

    protected FooterContainer footer() {
        return page(FooterContainer.class);
    }
}
