package oleksandrdiachenko.pricechecker.component;

import oleksandrdiachenko.pricechecker.AbstractTest;
import oleksandrdiachenko.pricechecker.helper.FileHelper;
import oleksandrdiachenko.pricechecker.pageobject.MainPage;
import org.junit.jupiter.api.Test;

import java.io.File;

import static oleksandrdiachenko.pricechecker.helper.SelenidePageWrapper.page;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPageTest extends AbstractTest {

    private final File file = new FileHelper().getFile("file/testFile.xlsx");

    @Test
    public void shouldCheckButtonDisabledWhenFileNotSelected() {
        assertThat(page(MainPage.class).isCheckEnabled()).isFalse();
    }

    @Test
    public void shouldCheckButtonDisabledWhenUrlInputEmpty() {
        page(MainPage.class).clearUrlInput();

        assertThat(page(MainPage.class).isCheckEnabled()).isFalse();
    }

    @Test
    public void shouldCheckButtonDisabledWhenUrlInputIsZero() {
        page(MainPage.class).setUrlInput(0);

        assertThat(page(MainPage.class).isCheckEnabled()).isFalse();
    }

    @Test
    public void shouldCheckButtonDisabledWhenInsertInputEmpty() {
        page(MainPage.class).clearInsertInput();

        assertThat(page(MainPage.class).isCheckEnabled()).isFalse();
    }

    @Test
    public void shouldCheckButtonDisabledWhenInsertInputIsZero() {
        page(MainPage.class).setInsertInput(0);

        assertThat(page(MainPage.class).isCheckEnabled()).isFalse();
    }

    @Test
    public void shouldCheckButtonEnabledWhenFileSelected() {
        page(MainPage.class).selectFile(file);

        assertThat(page(MainPage.class).isCheckEnabled()).isTrue();
    }

    @Test
    public void shouldCheckButtonEnabledWhenInsertInputNotEmpty() {
        page(MainPage.class)
                .selectFile(file)
                .clearInsertInput()
                .setInsertInput(1);

        assertThat(page(MainPage.class).isCheckEnabled()).isTrue();
    }

    @Test
    public void shouldCheckButtonEnabledWhenUrlInputNotEmpty() {
        page(MainPage.class)
                .selectFile(file)
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

    @Test
    public void shouldDisplayFileNameWhenFileSelected() {
        page(MainPage.class).selectFile(file);

        assertThat(page(MainPage.class).getTextFromFileSelector()).isEqualTo(file.getName());
    }
}