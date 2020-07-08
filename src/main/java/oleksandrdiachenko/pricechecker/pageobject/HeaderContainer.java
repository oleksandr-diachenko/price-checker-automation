package oleksandrdiachenko.pricechecker.pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.linkText;

public class HeaderContainer {

    private final SelenideElement home = $(linkText("Home"));
    private final SelenideElement statuses = $(linkText("Statuses"));
    private final SelenideElement dropdown = $(id("userDropdown"));

    public String getHomeLink() {
        return home.getAttribute("routerLink");
    }

    public String getStatusesLink() {
        return statuses.getAttribute("routerLink");
    }

    public String getUsernameOnDropdown() {
        return dropdown.getText();
    }
}
