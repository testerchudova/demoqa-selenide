package helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;

import java.net.URI;
import java.net.URL;
import java.util.stream.Collectors;

public class Attach {

    public static boolean videoEnabled = true;

    public static void attachAll() {
        screenshotAs("Last screenshot");
        pageSource();
        browserConsoleLogs();
        url();

        if (videoEnabled && Selenide.sessionId() != null) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            videoUrl();
            addVideo();
        }
    }

    @Attachment(value = "{attachName}", type = "image/png", fileExtension = ".png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver())
                .getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/html", fileExtension = ".html")
    public static String pageSource() {
        return WebDriverRunner.getWebDriver().getPageSource();
    }

    @Attachment(value = "Browser console logs", type = "text/plain")
    public static String browserConsoleLogs() {
        try {
            var driver = WebDriverRunner.getWebDriver();
            var availableLogTypes = driver.manage().logs().getAvailableLogTypes();

            if (!availableLogTypes.contains(LogType.BROWSER)) {
                return "Browser logs not available. Available log types: " + availableLogTypes;
            }

            return driver.manage().logs().get(LogType.BROWSER).getAll().stream()
                    .map(Object::toString)
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            return "Failed to retrieve browser logs: " + e.getMessage();
        }
    }

    @Attachment(value = "URL", type = "text/plain")
    public static String url() {
        return WebDriverRunner.url();
    }

    @Attachment(value = "Video URL", type = "text/plain")
    public static String videoUrl() {
        return buildVideoUrl();
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo() {
        return "<html><body><video width='100%' height='100%' controls autoplay>" +
                "<source src='" + buildVideoUrl() + "' type='video/mp4'>" +
                "</video></body></html>";
    }

    private static String buildVideoUrl() {
        String remoteUrl = System.getProperty("remoteUrl");

        if (remoteUrl == null || remoteUrl.isBlank()) {
            throw new RuntimeException("remoteUrl is empty");
        }

        if (Selenide.sessionId() == null) {
            throw new RuntimeException("sessionId is null");
        }

        return remoteUrl.replace(
                "/wd/hub",
                "/video/" + Selenide.sessionId() + ".mp4"
        );
    }

    public static URL getVideoUrl() {
        try {
            return URI.create(buildVideoUrl()).toURL();
        } catch (Exception e) {
            throw new RuntimeException("Failed to build video URL", e);
        }
    }
}