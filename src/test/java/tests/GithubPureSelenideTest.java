package tests;
import testbase.GithubTestBase;
import com.codeborne.selenide.Condition;

// Подключила логгер Selenide,
// чтобы добавить listener Allure для записи действий теста
import com.codeborne.selenide.logevents.SelenideLogger;

// Подключила listener Allure,
// чтобы действия отображались в отчете
import io.qameta.allure.selenide.AllureSelenide;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.withText;
import static org.openqa.selenium.By.linkText;

public class GithubPureSelenideTest extends GithubTestBase {

        // Указала свой репозиторий,
        // в котором буду проверять наличие Issue
        private static final String REPOSITORY =
                "testerchudova/demoqa-selenide";

        // Указала номер созданного Issue
        private static final int ISSUE = 1;

        @Test
        void testIssueWithPureSelenide() {

            // Подключаю Allure listener,
            // чтобы действия браузера записывались в отчет
            SelenideLogger.addListener(
                    "allure",
                    new AllureSelenide()
            );

            // В этом варианте использую чистый Selenide
            // без step() и без @Step

            // Открываю главную страницу GitHub
            open("https://github.com");

            // Выполняю поиск своего репозитория
            $("button[aria-label='Search or jump to…']").click();

            $("#query-builder-test")
                    .setValue(REPOSITORY)
                    .pressEnter();

            // Открываю найденный репозиторий
            $("a[href='/" + REPOSITORY + "']")
                    .shouldBe(Condition.visible)
                    .click();

            // Перехожу во вкладку Issues
            $("#issues-tab").click();

            // Финальная проверка
            // убеждаюсь, что Issue отображается на странице
            $(withText("#" + ISSUE))
                    .should(Condition.exist);
        }
    }
