package oleksandrdiachenko.pricechecker.component;

import oleksandrdiachenko.pricechecker.AbstractTest;
import oleksandrdiachenko.pricechecker.helper.FileHelper;
import oleksandrdiachenko.pricechecker.pageobject.MainPage;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.Test;

import java.io.File;

import static oleksandrdiachenko.pricechecker.helper.SelenidePageWrapper.page;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPageTest extends AbstractTest {

    private static final String INSERT_COLUMN_NUMBER_IS_REQUIRED = "Insert column number is required";
    private static final String URL_COLUMN_NUMBER_IS_REQUIRED = "Url column number is required";
    private static final String NUMBER_SHOULD_BE_AT_LEAST_1 = "Number should be at least 1";
    private static final String FILE_IS_REQUIRED = "File is required";
    private final File file = new FileHelper().getFile("file/testFile.xlsx");

    @Test
    void shouldDisplayErrorsWhenInputsNotSelected() {
        main()
                .clearInsertInput()
                .clearUrlInput();
        getInvalidFeedbackAssert()
                .containsExactlyInAnyOrder(INSERT_COLUMN_NUMBER_IS_REQUIRED, URL_COLUMN_NUMBER_IS_REQUIRED);

        main()
                .setUrlInput(0)
                .setInsertInput(0);
        getInvalidFeedbackAssert()
                .containsExactlyInAnyOrder(NUMBER_SHOULD_BE_AT_LEAST_1, NUMBER_SHOULD_BE_AT_LEAST_1);

        main()
                .setInsertInput(1)
                .setUrlInput(1)
                .clickCheck();
        getInvalidFeedbackAssert()
                .containsExactlyInAnyOrder(FILE_IS_REQUIRED);

    }

    private ListAssert<String> getInvalidFeedbackAssert() {
        return assertThat(main().getInvalidFeedback());
    }

    private MainPage main() {
        return page(MainPage.class);
    }
}