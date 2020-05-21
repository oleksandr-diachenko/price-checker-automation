package oleksandrdiachenko.pricechecker.component;

import oleksandrdiachenko.pricechecker.AbstractTest;
import oleksandrdiachenko.pricechecker.helper.FileHelper;
import oleksandrdiachenko.pricechecker.pageobject.MainPage;
import org.junit.jupiter.api.Test;

import java.io.File;

import static oleksandrdiachenko.pricechecker.helper.SelenidePageWrapper.page;
import static oleksandrdiachenko.pricechecker.pageobject.FileType.XLS;
import static oleksandrdiachenko.pricechecker.pageobject.FileType.XLSX;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPageTest extends AbstractTest {

    private final File file = new FileHelper().getFile("file/testFile.xlsx");

    @Test
    public void shouldCheckButtonDisabledWhenFileNotSelected() {
        assertThat(main().isCheckEnabled()).isFalse();
    }

    @Test
    public void shouldCheckButtonDisabledWhenUrlInputEmpty() {
        main().clearUrlInput();

        assertThat(page(MainPage.class).isCheckEnabled()).isFalse();
    }

    @Test
    public void shouldCheckButtonDisabledWhenUrlInputIsZero() {
        page(MainPage.class).setUrlInput(0);

        assertThat(main().isCheckEnabled()).isFalse();
    }

    @Test
    public void shouldCheckButtonDisabledWhenInsertInputEmpty() {
        main().clearInsertInput();

        assertThat(main().isCheckEnabled()).isFalse();
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
        main()
                .selectFile(file)
                .clearInsertInput()
                .setInsertInput(1);

        assertThat(main().isCheckEnabled()).isTrue();
    }

    @Test
    public void shouldCheckButtonEnabledWhenUrlInputNotEmpty() {
        main()
                .selectFile(file)
                .clearUrlInput()
                .setUrlInput(1);

        assertThat(main().isCheckEnabled()).isTrue();
    }

    @Test
    public void shouldInputsHaveValueOneByDefault() {
        int urlValue = main().getUrlInputValue();
        int insertValue = main().getInsertInputValue();

        assertThat(urlValue).isEqualTo(1);
        assertThat(insertValue).isEqualTo(1);
    }

    @Test
    public void shouldDisplayFileNameWhenFileSelected() {
        page(MainPage.class).selectFile(file);

        assertThat(page(MainPage.class).getTextFromFileSelector()).isEqualTo(file.getName());
    }

    @Test
    public void shouldContainsExactlyAcceptableFileTypes() {
        assertThat(page(MainPage.class).getAcceptableFileTypes())
                .containsExactlyInAnyOrder(XLS, XLSX);
    }

    private MainPage main() {
        return page(MainPage.class);
    }
}