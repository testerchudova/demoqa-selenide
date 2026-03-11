package pages.components;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    public void setDate(String dayValue, String monthValue, String yearValue) {
        $(".react-datepicker").shouldBe(visible);

        $(".react-datepicker__month-select").shouldBe(visible).click();
        $(".react-datepicker__month-select option[value='" + monthValue + "']").click();

        $(".react-datepicker__year-select").shouldBe(visible).click();
        $(".react-datepicker__year-select option[value='" + yearValue + "']").click();

        $(".react-datepicker__day--0" + dayValue +
                ":not(.react-datepicker__day--outside-month)")
                .click();
    }
}