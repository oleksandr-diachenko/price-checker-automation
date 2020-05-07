package oleksandrdiachenko.pricechecker.pageobject;

import lombok.Getter;

import java.util.Arrays;

import static java.lang.String.format;

@Getter
public enum SocialNetwork {

    FACEBOOK("https://www.facebook.com/aleksander.diachenko"),
    LINKEDIN("https://www.linkedin.com/in/alexander-diachenko/"),
    GITHUB("https://github.com/oleksandr-diachenko"),
    TELEGRAM("https://t.me/Oleksandr_Diachenko"),
    EMAIL("mailto:mail.positiv@gmail.com");

    private final String link;

    SocialNetwork(String link) {
        this.link = link;
    }

    public static SocialNetwork getByLink(String link) {
        return Arrays.stream(SocialNetwork.values())
                .filter(socialNetwork -> socialNetwork.link.equalsIgnoreCase(link))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("Social network with link %s not found", link)));
    }
}
