package oleksandrdiachenko.pricechecker.component;

import oleksandrdiachenko.pricechecker.AbstractTest;
import oleksandrdiachenko.pricechecker.pageobject.FooterContainer;
import oleksandrdiachenko.pricechecker.pageobject.MainPage;
import oleksandrdiachenko.pricechecker.pageobject.SocialNetwork;
import org.junit.jupiter.api.Test;

import static oleksandrdiachenko.pricechecker.pageobject.SocialNetwork.GITHUB;
import static oleksandrdiachenko.pricechecker.helper.SelenidePageWrapper.page;
import static org.assertj.core.api.Assertions.assertThat;

public class FooterContainerTest extends AbstractTest {

    @Test
    void shouldPresentFooterText() {
        assertThat(footer().getCopyright()).isEqualTo("© 2019 Copyright: Oleksandr Diachenko");
        assertThat(footer().getCopyrightLink()).isEqualTo(GITHUB.getLink());
        assertThat(footer().getSocialNetworks())
                .containsExactlyInAnyOrder(SocialNetwork.values());
    }

    private FooterContainer footer() {
        return page(MainPage.class).footer();
    }
}
