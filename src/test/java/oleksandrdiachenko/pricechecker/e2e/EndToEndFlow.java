package oleksandrdiachenko.pricechecker.e2e;

import com.codeborne.selenide.Configuration;
import oleksandrdiachenko.pricechecker.AbstractTest;
import oleksandrdiachenko.pricechecker.helper.FileHelper;
import oleksandrdiachenko.pricechecker.helper.PageCondition;
import oleksandrdiachenko.pricechecker.helper.PageWait;
import oleksandrdiachenko.pricechecker.pageobject.AlertContainer;
import oleksandrdiachenko.pricechecker.pageobject.MainPage;
import oleksandrdiachenko.pricechecker.pageobject.StatusesPage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static io.restassured.RestAssured.delete;
import static oleksandrdiachenko.pricechecker.helper.SelenidePageWrapper.page;
import static oleksandrdiachenko.pricechecker.pageobject.Status.IN_PROGRESS;
import static org.assertj.core.api.Assertions.assertThat;

public class EndToEndFlow extends AbstractTest {

    private static final String ADDED_TO_QUOTE = "Successfully added to queue!";
    private static final String DOWNLOAD = "Download";
    private final File file = new FileHelper().getFile("file/testFile.xlsx");
    private File downloadedFile;

    @BeforeEach
    void clearTables() {
        Configuration.fileDownload = PROXY;
        Configuration.proxyEnabled = true;
        delete("filestatuses");
        delete("files");
    }

    @Test
    void shouldCompletePriceCheck() {
        main()
                .setUrlInput(1)
                .setInsertInput(2)
                .selectFile(file)
                .clickCheck();

        assertThat(alert().getMessage()).isEqualTo(ADDED_TO_QUOTE);

        main().clickStatuses();

        List<String> lastRecord = getLastRecord();
        assertThat(lastRecord).contains(file.getName(), IN_PROGRESS.name());
        assertThat(lastRecord).doesNotContain(DOWNLOAD);

        new PageWait<>(statuses())
                .withTimeout(Duration.ofSeconds(120))
                .pollingEvery(Duration.ofSeconds(3))
                .until(PageCondition.lastRowContainsText(DOWNLOAD));

        downloadedFile = statuses().download(Long.parseLong(lastRecord.get(0)));
    }

    private List<String> getLastRecord() {
        List<List<String>> table = statuses().getTable();
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

    @AfterEach
    void cleanup() throws IOException {
        FileUtils.cleanDirectory(new File(Configuration.downloadsFolder));
    }
}
