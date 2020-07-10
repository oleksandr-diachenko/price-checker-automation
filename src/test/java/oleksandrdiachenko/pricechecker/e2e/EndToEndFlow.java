package oleksandrdiachenko.pricechecker.e2e;

import oleksandrdiachenko.pricechecker.AbstractTest;
import oleksandrdiachenko.pricechecker.helper.FileHelper;
import oleksandrdiachenko.pricechecker.helper.PageCondition;
import oleksandrdiachenko.pricechecker.helper.PageWait;
import oleksandrdiachenko.pricechecker.pageobject.AlertContainer;
import oleksandrdiachenko.pricechecker.pageobject.MainPage;
import oleksandrdiachenko.pricechecker.pageobject.StatusesPage;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static oleksandrdiachenko.pricechecker.helper.SelenidePageWrapper.page;
import static oleksandrdiachenko.pricechecker.pageobject.Status.COMPLETED;
import static oleksandrdiachenko.pricechecker.pageobject.Status.IN_PROGRESS;
import static org.assertj.core.api.Assertions.assertThat;

public class EndToEndFlow extends AbstractTest {

    private static final String ADDED_TO_QUOTE = "Successfully added to queue!";
    private static final String DOWNLOAD = "Download";
    private final File file = new FileHelper().getFile("file/testFile.xlsx");

    @Test
    void shouldCompletePriceCheck() {
        main()
                .setUrlInput(1)
                .setInsertInput(2)
                .selectFile(file)
                .clickCheck();

        assertThat(alert().getMessage()).isEqualTo(ADDED_TO_QUOTE);

        main().clickStatuses();

        new PageWait<>(statuses())
                .withTimeout(Duration.ofSeconds(120))
                .pollingEvery(Duration.ofSeconds(3))
                .until(PageCondition.lastRowContainsText(IN_PROGRESS.name()));

        assertThat(getLastRecord())
                .contains(file.getName(), IN_PROGRESS.name())
                .doesNotContain(DOWNLOAD);

        new PageWait<>(statuses())
                .withTimeout(Duration.ofSeconds(120))
                .pollingEvery(Duration.ofSeconds(3))
                .until(PageCondition.lastRowContainsText(DOWNLOAD));

        assertThat(getLastRecord()).contains(file.getName(), COMPLETED.name());
    }

    private List<String> getLastRecord() {
        List<List<String>> table = statuses().getLastTablePage();
        return table.get(table.size() - 1);
    }

    private StatusesPage statuses() {
        return page(StatusesPage.class);
    }

    private MainPage main() {
        return page(MainPage.class);
    }

    private AlertContainer alert() {
        return main().alert();
    }
}
