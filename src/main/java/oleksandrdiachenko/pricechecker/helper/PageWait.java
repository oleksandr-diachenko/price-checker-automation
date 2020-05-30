package oleksandrdiachenko.pricechecker.helper;

import lombok.extern.slf4j.Slf4j;
import oleksandrdiachenko.pricechecker.pageobject.AbstractPage;
import org.openqa.selenium.support.ui.FluentWait;

@Slf4j
public class PageWait<T extends AbstractPage> extends FluentWait<T> {

    public PageWait(T page) {
        super(page);
    }
}
