package testbase;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;



public class TestBaseForPr {

    @BeforeAll
    static void setupSelenideConfig() {

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.screenshots = true;
        Configuration.savePageSource = true;

    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }
}