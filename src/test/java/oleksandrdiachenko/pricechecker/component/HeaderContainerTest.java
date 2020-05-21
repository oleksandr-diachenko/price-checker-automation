package oleksandrdiachenko.pricechecker.component;

import oleksandrdiachenko.pricechecker.AbstractTest;
import oleksandrdiachenko.pricechecker.pageobject.HeaderContainer;
import oleksandrdiachenko.pricechecker.pageobject.MainPage;
import org.junit.jupiter.api.Test;

import static oleksandrdiachenko.pricechecker.pageobject.SocialNetwork.*;
import static oleksandrdiachenko.pricechecker.helper.SelenidePageWrapper.page;
import static org.assertj.core.api.Assertions.assertThat;

public class HeaderContainerTest extends AbstractTest {

    @Test
    void shouldPresentSocialNetwork() {
        assertThat(header().getSocialNetworks())
                .containsExactlyInAnyOrder(FACEBOOK, LINKEDIN, GITHUB, TELEGRAM, EMAIL);
    }

    private HeaderContainer header() {
        return page(MainPage.class).header();
    }
}
