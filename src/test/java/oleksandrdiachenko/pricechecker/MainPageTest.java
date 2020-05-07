package oleksandrdiachenko.pricechecker;

import oleksandrdiachenko.pricechecker.pageobject.MainPage;
import org.junit.jupiter.api.Test;

import static oleksandrdiachenko.pricechecker.util.SelenidePageWrapper.page;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPageTest extends AbstractTest {

    @Test
    public void shouldCheckButtonDisabledWhenFileNotChosen() {
        assertThat(page(MainPage.class).isCheckEnabled()).isFalse();
    }

    @Test
    public void shouldInputsHaveValueOneByDefault() {
        int urlValue = page(MainPage.class).getUrlInputValue();
        int insertValue = page(MainPage.class).getInsertInputValue();

        assertThat(urlValue).isEqualTo(1);
        assertThat(insertValue).isEqualTo(1);
    }
}