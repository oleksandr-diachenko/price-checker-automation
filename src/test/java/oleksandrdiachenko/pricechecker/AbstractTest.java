package oleksandrdiachenko.pricechecker;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import oleksandrdiachenko.pricechecker.environment.Environment;
import oleksandrdiachenko.pricechecker.pageobject.LoginPage;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static oleksandrdiachenko.pricechecker.helper.SelenidePageWrapper.page;
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
        restAssuredConfiguration();
        page(LoginPage.class).login(environment.username(), environment.password());
    }

    private void restAssuredConfiguration() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(environment.baseApiUri())
                .setPort(environment.apiPort())
                .setBasePath(environment.baseApiPath())
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.ANY).build();
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
