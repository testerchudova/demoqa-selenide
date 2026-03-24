package tests;

import com.codeborne.selenide.Condition;

// Подключила аннотацию @Step,
// чтобы оформлять действия как отдельные шаги в отчете Allure
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.withText;

// Создала отдельный класс для хранения шагов теста.
// Этот класс не запускается сам,
// его методы вызываются из тестов.
public class GithubSteps {

    @Step("Открыть главную страницу GitHub")
    public void openMainPage() {

        open("https://github.com");

    }

    // Этот метод выполняет поиск репозитория.
    // {repository} подставляет значение параметра
    // в текст шага в отчете Allure.
    @Step("Найти репозиторий {repository}")
    public void searchForRepository(String repository) {

        $("button[aria-label='Search or jump to…']").click();

        $("#query-builder-test")
                .setValue(repository)
                .pressEnter();

    }

    @Step("Открыть репозиторий {repository}")
    public void openRepository(String repository) {
        $("a[href='/" + repository + "']")
                .shouldBe(Condition.visible)
                .click();
    }

    @Step("Перейти в раздел Issues")
    public void openIssuesTab() {

        $("#issues-tab").click();

    }

    @Step("Проверить наличие Issue #{issueNumber}")
    public void shouldSeeIssueWithNumber(int issueNumber) {

        $(withText("#" + issueNumber))
                .should(Condition.exist);

    }
}