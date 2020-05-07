package oleksandrdiachenko.pricechecker;

import oleksandrdiachenko.pricechecker.pageobject.StatusesPage;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static oleksandrdiachenko.pricechecker.util.SelenidePageWrapper.page;
import static org.assertj.core.api.Assertions.assertThat;

public class StatusesPageTest extends AbstractTest {

    @Test
    public void shouldContainsTableHeader() {
        List<String> thead = Arrays.asList("Id", "Name", "Status", "Accepted time", "Download");

        assertThat(page(StatusesPage.class).getTable()).containsOnlyOnce(thead);
    }
}