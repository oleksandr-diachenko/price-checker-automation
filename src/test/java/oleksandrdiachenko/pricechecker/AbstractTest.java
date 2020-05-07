package oleksandrdiachenko.pricechecker;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import oleksandrdiachenko.pricechecker.environment.Environment;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Data
@Slf4j
public abstract class AbstractTest {

    protected Environment environment;

    @BeforeEach
    void before() {
        ConfigFactory.setProperty("env", getEnv());
        environment = ConfigFactory.create(Environment.class);
        selenideConfiguration();
    }

    private void selenideConfiguration() {
        Configuration.startMaximized = true;
        Configuration.baseUrl = environment.url();
    }

    private String getEnv() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("environment/environment.properties")) {
            Properties prop = new Properties();
            prop.load(is);
            String env = prop.getProperty("env");
            if (isEmpty(env)) {
                log.error("Env variable doesn't exist in environment.properties");
                throw new RuntimeException("Env variable doesn't exist in environment.properties");
            }
            return env;
        } catch (IOException e) {
            log.error("Can't read environment.properties", e);
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void after() {
        Selenide.closeWebDriver();
    }
}
