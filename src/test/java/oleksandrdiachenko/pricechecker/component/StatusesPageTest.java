package oleksandrdiachenko.pricechecker.component;

import oleksandrdiachenko.pricechecker.AbstractTest;
import oleksandrdiachenko.pricechecker.pageobject.MainPage;
import oleksandrdiachenko.pricechecker.pageobject.StatusesPage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static oleksandrdiachenko.pricechecker.helper.SelenidePageWrapper.page;
import static org.assertj.core.api.Assertions.assertThat;

public class StatusesPageTest extends AbstractTest {

    @Test
    public void shouldContainsTableHeader() {
        page(MainPage.class).clickStatuses();

        List<String> thead = List.of("Name", "Status", "Accepted time", "Download");
        assertThat(statuses().getLastTablePage()).containsOnlyOnce(thead);
    }

    private StatusesPage statuses() {
        return page(StatusesPage.class);
    }
}