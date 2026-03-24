package tests;

import testbase.GithubTestBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.withText;

public class GithubLambdaStepsTest extends GithubTestBase {

    // Указала свой репозиторий,
    // в котором буду проверять наличие Issue
    private static final String REPOSITORY =
            "testerchudova/demoqa-selenide";

    // Указала номер созданного Issue
    private static final int ISSUE = 1;

    @Test
    void testIssueWithLambdaSteps() {

        // Подключаю Allure listener,
        // чтобы действия записывались в отчет
        SelenideLogger.addListener(
                "allure",
                new AllureSelenide()
        );

        // Использую lambda step,
        // чтобы каждый шаг был виден в отчете

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