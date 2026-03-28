package helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;

import java.io.ByteArrayInputStream;

public class Attach {

    public static void screenshotAs(String name) {
        byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver())
                .getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
    }

    public static void pageSource() {
        String pageSource = Selenide.webdriver().driver().source();
        Allure.addAttachment("Page source", "text/html", pageSource, ".html");
    }

    public static void browserConsoleLogs() {
        String logs = String.join(
                "\n",
                Selenide.getWebDriverLogs(LogType.BROWSER)
        );
        Allure.addAttachment("Browser console logs", "text/plain", logs);
    }

    public static void addVideo() {
        String remoteUrl = System.getProperty("remoteUrl");

        if (remoteUrl == null || remoteUrl.isBlank() || Selenide.sessionId() == null) {
            return;
        }

        String sessionId = Selenide.sessionId().toString();
        String videoUrl = remoteUrl.replace("/wd/hub", "") + "/video/" + sessionId + ".mp4";

        Allure.addAttachment("Video", "text/uri-list", videoUrl);
    }
}