package oleksandrdiachenko.pricechecker;

import oleksandrdiachenko.pricechecker.pageobject.MainPage;
import oleksandrdiachenko.pricechecker.util.FileHelper;
import org.junit.jupiter.api.Test;

import java.io.File;

import static oleksandrdiachenko.pricechecker.util.SelenidePageWrapper.page;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPageTest extends AbstractTest {

    private final File file = new FileHelper().getFile("file/testFile.xlsx");

    @Test
    public void shouldCheckButtonDisabledWhenFileNotChosen() {
        assertThat(page(MainPage.class).isCheckEnabled()).isFalse();
    }

    @Test
    public void shouldCheckButtonDisabledWhenUrlInputEmpty() {
        page(MainPage.class).clearUrlInput();

        assertThat(page(MainPage.class).isCheckEnabled()).isFalse();
    }

    @Test
    public void shouldCheckButtonDisabledWhenInsertInputEmpty() {
        page(MainPage.class).clearInsertInput();

        assertThat(page(MainPage.class).isCheckEnabled()).isFalse();
    }

    @Test
    public void shouldCheckButtonEnabledWhenInsertInputNotEmpty() {
        page(MainPage.class)
                .chooseFile(file)
                .clearInsertInput()
                .setInsertInput(1);

        assertThat(page(MainPage.class).isCheckEnabled()).isTrue();
    }

    @Test
    public void shouldCheckButtonEnabledWhenUrlInputNotEmpty() {
        page(MainPage.class)
                .chooseFile(file)
                .clearUrlInput()
                .setUrlInput(1);

        assertThat(page(MainPage.class).isCheckEnabled()).isTrue();
    }

    @Test
    public void shouldInputsHaveValueOneByDefault() {
        int urlValue = page(MainPage.class).getUrlInputValue();
        int insertValue = page(MainPage.class).getInsertInputValue();

        assertThat(urlValue).isEqualTo(1);
        assertThat(insertValue).isEqualTo(1);
    }
}