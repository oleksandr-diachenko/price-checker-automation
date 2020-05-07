package oleksandrdiachenko.pricechecker.pageobject;

import lombok.NoArgsConstructor;

import static com.codeborne.selenide.Selenide.page;

@NoArgsConstructor
public abstract class AbstractPage {

    protected HeaderContainer header() {
        return page(HeaderContainer.class);
    }

    protected FooterContainer footer() {
        return page(FooterContainer.class);
    }
}
