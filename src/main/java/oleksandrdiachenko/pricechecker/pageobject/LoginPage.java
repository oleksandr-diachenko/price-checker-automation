package oleksandrdiachenko.pricechecker.pageobject;

import com.codeborne.selenide.SelenideElement;
import oleksandrdiachenko.pricechecker.annotation.RelativeUrl;

import static com.codeborne.selenide.Selenide.$;
import static oleksandrdiachenko.pricechecker.helper.SelenidePageWrapper.page;
import static org.openqa.selenium.By.id;

/**
 * @author : Oleksandr Diachenko
 * @since : 7/8/2020
 **/
@RelativeUrl("login")
public class LoginPage extends AbstractPage {

    private final SelenideElement username = $(id("username"));
    private final SelenideElement password = $(id("password"));
    private final SelenideElement login = $(id("login"));

    public MainPage login(String user, String pass) {
        username.setValue(user);
        password.setValue(pass);
        login.click();
        return page(MainPage.class);
    }
}
