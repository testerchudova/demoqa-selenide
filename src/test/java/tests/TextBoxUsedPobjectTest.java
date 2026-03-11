package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import testdata.TestTextBoxBase;
import static testdata.TestTextBoxData.*;

public class TextBoxUsedPobjectTest extends TestTextBoxBase {

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void fillTextBoxFormTest() {
        textBoxPage.openPage()
                .setFullName(FULL_NAME)
                .setEmail(EMAIL)
                .setCurrentAddress(CURRENT_ADDRESS)
                .setPermanentAddress(PERMANENT_ADDRESS)
                .submitForm()
                .checkResult(FULL_NAME)
                .checkResult(EMAIL)
                .checkResult(CURRENT_ADDRESS)
                .checkResult(PERMANENT_ADDRESS);
    }
}