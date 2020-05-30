package oleksandrdiachenko.pricechecker.pageobject;

import lombok.NoArgsConstructor;

import static com.codeborne.selenide.Selenide.page;

@NoArgsConstructor
public abstract class AbstractPage {

    public HeaderContainer header() {
        return page(HeaderContainer.class);
    }

    public FooterContainer footer() {
        return page(FooterContainer.class);
    }

    public AlertContainer alert() {
        return page(AlertContainer.class);
    }
}
