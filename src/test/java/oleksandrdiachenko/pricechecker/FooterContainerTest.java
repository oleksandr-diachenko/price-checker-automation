package oleksandrdiachenko.pricechecker;

import oleksandrdiachenko.pricechecker.pageobject.MainPage;
import org.junit.jupiter.api.Test;

import static oleksandrdiachenko.pricechecker.pageobject.SocialNetwork.GITHUB;
import static oleksandrdiachenko.pricechecker.util.SelenidePageWrapper.page;
import static org.assertj.core.api.Assertions.assertThat;

public class FooterContainerTest extends AbstractTest {

    @Test
    void shouldPresentFooterText() {
        assertThat(page(MainPage.class).footer().getCopyright())
                .isEqualTo("© 2019 Copyright: Oleksandr Diachenko");
        assertThat(page(MainPage.class).footer().getCopyrightLink())
                .isEqualTo(GITHUB.getLink());
    }
}
