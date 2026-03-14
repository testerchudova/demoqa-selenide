package pages.components;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {

    public void setDate(String dayValue, String monthValue, String yearValue) {
        $(".react-datepicker").shouldBe(visible);

        $(".react-datepicker__year-select").click();
        $$(".react-datepicker__year-select option").findBy(text(yearValue)).click();

        $(".react-datepicker__month-select").click();
        $$(".react-datepicker__month-select option").findBy(text(monthValue)).click();

        String dayForClick = String.valueOf(Integer.parseInt(dayValue));

        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(exactText(dayForClick))
                .click();
    }
}