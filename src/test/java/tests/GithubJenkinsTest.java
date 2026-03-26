package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import testbase.GithubTestBase;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class GithubJenkinsTest extends GithubTestBase {

    private static final String REPOSITORY =
            "testerchudova/demoqa-selenide";


    private static final int ISSUE = 1;

    @Test
    @Tag("jankins")
    void testIssueWithLambdaSteps() {


        SelenideLogger.addListener(
                "allure",
                new AllureSelenide()
        );


        step("Открыть главную страницу", () -> {
            open("https://github.com");
        });

        step("Найти репозиторий", () -> {
            $("button[aria-label='Search or jump to…']").click();

            $("#query-builder-test")
                    .setValue(REPOSITORY)
                    .pressEnter();
        });

        step("Открыть репозиторий", () -> {
            $("a[href='/" + REPOSITORY + "']")
                    .shouldBe(Condition.visible)
                    .click();
        });

        step("Перейти в Issues", () -> {
            $("#issues-tab").click();
        });

        step("Проверить наличие Issue", () -> {
            $(withText("#" + ISSUE))
                    .should(Condition.exist);
        });
    }
}