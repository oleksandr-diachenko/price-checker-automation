package oleksandrdiachenko.pricechecker;

import oleksandrdiachenko.pricechecker.pageobject.MainPage;
import org.junit.jupiter.api.Test;

import static oleksandrdiachenko.pricechecker.pageobject.SocialNetwork.*;
import static oleksandrdiachenko.pricechecker.util.SelenidePageWrapper.page;
import static org.assertj.core.api.Assertions.assertThat;

public class HeaderContainerTest extends AbstractTest{

    @Test
    void shouldPresentSocialNetwork() {
        assertThat(page(MainPage.class).header().getSocialNetworks())
                .containsExactlyInAnyOrder(FACEBOOK, LINKEDIN, GITHUB, TELEGRAM, EMAIL);
    }
}
