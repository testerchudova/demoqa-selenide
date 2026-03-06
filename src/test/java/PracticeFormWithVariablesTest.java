package testdata;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static testdata.TestDataOne.*;

public class PracticeFormWithVariablesTest extends TestBaseForPr {

    @Test
    void fillAllFieldsTest() {
        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("#userEmail").setValue(EMAIL);
        $("#genterWrapper").$(byText(GENDER)).click();
        $("#userNumber").setValue(MOBILE);

        $("#dateOfBirthInput").scrollTo().click();
        $(".react-datepicker").shouldBe(visible);
        $(".react-datepicker__month-select").shouldBe(visible).click();
        $(".react-datepicker__month-select option[value='" + MONTH_VALUE + "']").click();
        $(".react-datepicker__year-select").shouldBe(visible).click();
        $(".react-datepicker__year-select option[value='" + YEAR_VALUE + "']").click();
        $(".react-datepicker__day--" + DAY_VALUE + ":not(.react-datepicker__day--outside-month)").click();

        $("#subjectsInput").setValue(SUBJECT).pressEnter();
        $("#hobbiesWrapper").$(byText(HOBBY)).click();
        $("#uploadPicture").uploadFromClasspath(PICTURE);
        $("#currentAddress").setValue(ADDRESS);

        $("#state").click();
        $("#stateCity-wrapper").$(byText(STATE)).click();

        $("#city").click();
        $("#stateCity-wrapper").$(byText(CITY)).click();

        $("#submit").click();

        $(".modal-content").shouldBe(visible);
        $("#example-modal-sizes-title-lg").shouldHave(text(SUCCESS_MESSAGE));

        $(".table-responsive").shouldBe(visible);
        $(".table-responsive").shouldHave(text(FIRST_NAME + " " + LAST_NAME));
        $(".table-responsive").shouldHave(text(EMAIL));
        $(".table-responsive").shouldHave(text(GENDER));
        $(".table-responsive").shouldHave(text(MOBILE));
        $(".table-responsive").shouldHave(text(DATE_EXPECTED));
        $(".table-responsive").shouldHave(text(SUBJECT));
        $(".table-responsive").shouldHave(text(HOBBY));
        $(".table-responsive").shouldHave(text(PICTURE));
        $(".table-responsive").shouldHave(text(ADDRESS));
        $(".table-responsive").shouldHave(text(STATE_CITY));
    }

    @Test
    void fillMandatoryFieldsTest() {
        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("#genterWrapper").$(byText(GENDER)).click();
        $("#userNumber").setValue(MOBILE);

        $("#submit").click();

        $(".modal-content").shouldBe(visible);
        $("#example-modal-sizes-title-lg").shouldHave(text(SUCCESS_MESSAGE));

        $(".table-responsive").shouldBe(visible);
        $(".table-responsive").shouldHave(text(FIRST_NAME + " " + LAST_NAME));
        $(".table-responsive").shouldHave(text(GENDER));
        $(".table-responsive").shouldHave(text(MOBILE));
    }
}