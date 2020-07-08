package oleksandrdiachenko.pricechecker.pageobject;

import com.codeborne.selenide.SelenideElement;

import java.util.HashSet;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;

public class FooterContainer {

    private final SelenideElement copyright = $(".footer-copyright");
    private final SelenideElement socialNetwork = $(id("social-network"));

    public Set<SocialNetwork> getSocialNetworks() {
        Set<SocialNetwork> socialNetworks = new HashSet<>();
        for (SelenideElement network : socialNetwork.findAll("[href]")) {
            socialNetworks.add(SocialNetwork.getByLink(network.getAttribute("href")));
        }
        return socialNetworks;
    }

    public String getCopyright() {
        return copyright.getText();
    }

    public String getCopyrightLink() {
        return copyright.find("[href]").getAttribute("href");
    }
}
