package oleksandrdiachenko.pricechecker.pageobject;

import lombok.Getter;

@Getter
public enum SocialNetwork {

    FACEBOOK("fa-facebook"),
    LINKEDIN("fa-linkedin"),
    GITHUB("fa-github"),
    TELEGRAM("fa-telegram"),
    EMAIL("fa-at");

    private final String iconClass;

    SocialNetwork(String iconClass) {
        this.iconClass = iconClass;
    }
}
