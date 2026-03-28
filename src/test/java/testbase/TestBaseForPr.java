package testbase;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBaseForPr {

    @BeforeAll
    static void setupSelenideConfig() {
        Configuration.baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "128.0");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;

        Configuration.screenshots = true;
        Configuration.savePageSource = true;

        String remoteUrl = System.getProperty("remoteUrl");
        if (remoteUrl != null && !remoteUrl.isBlank()) {
            Configuration.remote = remoteUrl;

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", new java.util.HashMap<String, Object>() {{
                put("enableVNC", true);
                put("enableVideo", true);
                put("enableLog", true);
            }});

            Configuration.browserCapabilities = capabilities;
        }

        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @AfterEach
    void addAttachmentsAndCloseBrowser() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        Selenide.closeWebDriver();
    }
}