import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTest {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.screenshots = true;
        Configuration.savePageSource = true;

    }

    @BeforeEach
    void openForm() {
        open("/automation-practice-form");
    }
// Позитивные 1,2
    @Test
    void fillAllFields_submit_success() {

        $("#firstName").setValue("Katya");
        $("#lastName").setValue("Ivanova");
        $("#userEmail").setValue("katya@test.com");
        $(byText("Female")).click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").setValue("10 Oct 1995").pressEnter();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("testpic.jpg");
        $("#currentAddress").setValue("Moscow, Arbat 1");
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();

        $("#submit").click();
        $(".modal-content").shouldBe(visible);
        $("#example-modal-sizes-title-lg").shouldHave(com.codeborne.selenide.Condition.text("Thanks for submitting the form"));
    }

    @Test
    void fillMandatoryFields_submit_success() {

        $("#firstName").setValue("Katya");
        $("#lastName").setValue("Ivanova");
        $(byText("Female")).click();
        $("#userNumber").setValue("1234567890");

        $("#submit").click();
        $(".modal-content").shouldBe(visible);
    }

//Пустая форма не отправляется - Негативный 1
@Test
void submitEmptyForm_shouldNotShowModal() {
    $("#submit").click();
    $(".modal-content").shouldNotBe(visible);
}
//Ошибка в email - Негативный 2

    @Test
    void invalidEmail_shouldNotSubmit() {
        $("#firstName").setValue("Katya");
        $("#lastName").setValue("Ivanova");
        $("#userEmail").setValue("katya-at-test.com");
        $(byText("Female")).click();
        $("#userNumber").setValue("1234567890");

        $("#submit").click();
        $(".modal-content").shouldNotBe(visible);
    }
//Ошибка в номере телефона по кол. символов - Негативный 3
@Test
void shortMobile_shouldNotSubmit() {
    $("#firstName").setValue("Katya");
    $("#lastName").setValue("Ivanova");
    $(byText("Female")).click();
    $("#userNumber").setValue("12345");

    $("#submit").click();
    $(".modal-content").shouldNotBe(visible);
}
    //Отсутсвует гендер - Негативный 4
    @Test
    void withoutGender_shouldNotSubmit() {
        $("#firstName").setValue("Katya");
        $("#lastName").setValue("Ivanova");
        $("#userNumber").setValue("1234567890");

        $("#submit").click();
        $(".modal-content").shouldNotBe(visible);
    }
}