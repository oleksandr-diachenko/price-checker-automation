package oleksandrdiachenko.pricechecker.util;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.slf4j.Slf4j;
import oleksandrdiachenko.pricechecker.annotaion.RelativeUrl;
import oleksandrdiachenko.pricechecker.pageobject.AbstractPage;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public class SelenidePageWrapper {

    public static <T extends AbstractPage> T page(Class<T> page) {
        RelativeUrl relativeUrl = page.getAnnotation(RelativeUrl.class);
        openIfNeeded(relativeUrl.value());
        try {
            return page.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            log.error("Can't create new instance from {}", page.getSimpleName(), e);
            throw new RuntimeException(e);
        }
    }

    private static void openIfNeeded(String relativeUrl) {
        if (!WebDriverRunner.hasWebDriverStarted()) {
            Selenide.open(relativeUrl);
        } else {
            if (newUrl(relativeUrl)) {
                Selenide.open(relativeUrl);
            }
        }
    }

    private static boolean newUrl(String relativeUrl) {
        return !StringUtils.endsWith(WebDriverRunner.url(), relativeUrl);
    }
}
