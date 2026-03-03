import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.exist;

public class PracticeFormTest {

    @BeforeAll
    static void setupSelenideConfig() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
    }

    @BeforeEach
    void openPracticeForm() {
        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                """);
    }

    // Позитивные 1,2
    @Test
    void fillAllFieldsTest() {
        $("#firstName").setValue("Katya");
        $("#lastName").setValue("Ivanova");
        $("#userEmail").setValue("katya@test.com");
        $(byText("Female")).click();
        $("#userNumber").setValue("1234567890");

        $("#dateOfBirthInput").scrollTo().click();
        $(".react-datepicker").shouldBe(visible);
        $(".react-datepicker__month-select").shouldBe(visible).click();
        $(".react-datepicker__month-select option[value='9']").click();   // October
        $(".react-datepicker__year-select").shouldBe(visible).click();
        $(".react-datepicker__year-select option[value='1995']").click();
        $(".react-datepicker__day--010:not(.react-datepicker__day--outside-month)").click();
        $("#dateOfBirthInput").shouldHave(value("10 Oct 1995"));

        $("#subjectsInput").setValue("Maths").pressEnter();
        $(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("testpic.jpg");
        $("#currentAddress").setValue("Moscow, Arbat 1");
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();
        $("#submit").click();

        //Проверки по каждому полю

        $(".table-responsive").shouldBe(visible);
        $(".table-responsive").shouldHave(text("Student Name"));
        $(".table-responsive").shouldHave(text("Katya Ivanova"));
        $(".table-responsive").shouldHave(text("Student Email"));
        $(".table-responsive").shouldHave(text("katya@test.com"));
        $(".table-responsive").shouldHave(text("Gender"));
        $(".table-responsive").shouldHave(text("Female"));
        $(".table-responsive").shouldHave(text("Mobile"));
        $(".table-responsive").shouldHave(text("1234567890"));
        $("#dateOfBirthInput").shouldHave(value("10 Oct 1995"));
        $(".table-responsive").shouldHave(text("Subjects"));
        $(".table-responsive").shouldHave(text("Maths"));
        $(".table-responsive").shouldHave(text("Hobbies"));
        $(".table-responsive").shouldHave(text("Reading"));
        $(".table-responsive").shouldHave(text("Picture"));
        $(".table-responsive").shouldHave(text("testpic.jpg"));
        $(".table-responsive").shouldHave(text("Address"));
        $(".table-responsive").shouldHave(text("Moscow, Arbat 1"));
        $(".table-responsive").shouldHave(text("State and City"));
        $(".table-responsive").shouldHave(text("NCR Delhi"));

        $(".modal-content").shouldBe(visible);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }

    @Test
    void fillMandatoryFieldsTest() {
        $("#firstName").setValue("Katya");
        $("#lastName").setValue("Ivanova");
        $(byText("Female")).click();
        $("#userNumber").setValue("1234567890");

        $("#submit").click();

        $(".modal-content").shouldBe(visible);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldBe(visible);
        $(".table-responsive").shouldHave(text("Student Name"));
        $(".table-responsive").shouldHave(text("Katya Ivanova"));
        $(".table-responsive").shouldHave(text("Gender"));
        $(".table-responsive").shouldHave(text("Female"));
        $(".table-responsive").shouldHave(text("Mobile"));
        $(".table-responsive").shouldHave(text("1234567890"));
    }

    //Пустая форма не отправляется - Негативный 1
    @Test
    void submitEmptyForm_shouldNotShowModal() {
        $("#submit").click();
        $(".modal-content").shouldNotBe(visible);
    }
//Ошибка в email - Негативный 2

    @Test
    void invalidEmailShouldNotSubmitTest() {
        $("#firstName").setValue("Katya");
        $("#lastName").setValue("Ivanova");
        $("#userEmail").setValue("katya-at-test.com");
        $(byText("Female")).click();
        $("#userNumber").setValue("1234567890");

        $("#submit").click();

        $("#userEmail").shouldHave(value("katya-at-test.com"));
        $(".modal-content").shouldNotBe(visible);

    }

    //Ошибка в номере телефона по кол. символов - Негативный 3
    @Test
    void shortMobileShouldNotSubmitTest() {
        $("#firstName").setValue("Katya");
        $("#lastName").setValue("Ivanova");
        $(byText("Female")).click();
        $("#userNumber").setValue("12345");

        $("#submit").click();

        $("#userNumber").shouldHave(value("12345"));
        $(".modal-content").shouldNotBe(visible);
    }

    //Отсутсвует гендер - Негативный 4
    @Test
    void withoutGenderShouldNotSubmitTest() {
        $("#firstName").setValue("Katya");
        $("#lastName").setValue("Ivanova");
        $("#userNumber").setValue("1234567890");

        $("#submit").click();

        $("input[name='gender']:checked").shouldNot(exist);
        $(".modal-content").shouldNotBe(visible);
    }
}