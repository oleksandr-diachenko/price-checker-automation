package oleksandrdiachenko.pricechecker.pageobject;

import com.codeborne.selenide.SelenideElement;

import java.util.HashSet;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;

public class HeaderContainer {

    private final SelenideElement socialNetwork = $(".social-network");

    public Set<SocialNetwork> getSocialNetworks() {
        Set<SocialNetwork> socialNetworks = new HashSet<>();
        for (SelenideElement socialNetwork : socialNetwork.findAll("[href]")) {
            socialNetworks.add(SocialNetwork.getByLink(socialNetwork.getAttribute("href")));
        }
        return socialNetworks;
    }
}
