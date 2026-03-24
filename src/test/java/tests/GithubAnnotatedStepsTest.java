package tests;

// Подключила логгер Selenide,
// чтобы потом добавить listener Allure
// для записи действий теста

import com.codeborne.selenide.logevents.SelenideLogger;
import testbase.GithubTestBase;
// Подключила AllureSelenide,
// он нужен для записи шагов и логов в отчет Allure
import io.qameta.allure.selenide.AllureSelenide;

// Подключила аннотацию @Test,
// чтобы обозначить тестовый метод
import org.junit.jupiter.api.Test;

public class GithubAnnotatedStepsTest extends GithubTestBase {
    // Создала класс теста
    // Добавила константу с названием репозитория
    // который буду искать на GitHub
    private static final String REPOSITORY =
            "testerchudova/demoqa-selenide";

    // Добавила номер Issue,
    // который ранее создала вручную (#1)
    private static final int ISSUE = 1;


    // Создала тестовый метод,
    // который проверяет наличие Issue через шаги @Step
    @Test
    void testIssueWithAnnotatedSteps() {

        // Подключила Allure listener,
        // чтобы записывать действия браузера
        // и формировать отчет Allure
        SelenideLogger.addListener(
                "allure",
                new AllureSelenide()
        );

        // Создала объект класса GithubSteps,
        // в котором описаны шаги теста
        GithubSteps steps = new GithubSteps();

        // Открываю главную страницу GitHub
        steps.openMainPage();

        // Выполняю поиск своего репозитория
        // testerchudova/demoqa-selenide
        steps.searchForRepository(REPOSITORY);

        // Открываю найденный репозиторий
        steps.openRepository(REPOSITORY);

        // Перехожу во вкладку Issues
        steps.openIssuesTab();

        // Проверяю наличие созданного Issue #1
        steps.shouldSeeIssueWithNumber(ISSUE);
    }
}
