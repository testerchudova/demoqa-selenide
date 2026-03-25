package testbase;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;

public class GithubTestBase {

    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 60000;
        Configuration.timeout = 10000;
           }
}