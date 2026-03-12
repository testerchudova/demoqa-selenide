package tests;

import org.junit.jupiter.api.Test;
import testbase.TestTextBoxBase;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;

import static testdata.TestTextBoxData.*;

public class TextBoxTest extends TestTextBoxBase {

    @Test
    void TextBoxInputTest() {
        $("#userName").setValue(FULL_NAME);
        $("#userEmail").setValue(EMAIL);
        $("#currentAddress").setValue(CURRENT_ADDRESS);
        $("#permanentAddress").setValue(PERMANENT_ADDRESS);
        $("#submit").click();

        $("#output #name").shouldHave(text(FULL_NAME));
        $("#output #email").shouldHave(text(EMAIL));
        $("#output #currentAddress").shouldHave(text(CURRENT_ADDRESS));
        $("#output #permanentAddress").shouldHave(text(PERMANENT_ADDRESS));

    }


}
