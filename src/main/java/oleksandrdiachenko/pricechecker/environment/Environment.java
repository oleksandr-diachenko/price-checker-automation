package oleksandrdiachenko.pricechecker.environment;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:environment/${env}.properties"
})
public interface Environment extends Config {

    String url();

    String baseApiUri();

    int apiPort();

    String baseApiPath();

    String username();

    String password();
}