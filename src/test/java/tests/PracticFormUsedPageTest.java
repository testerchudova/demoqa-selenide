package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import testdata.TestBaseForPr;
import static testdata.TestDataOne.*;

public class PracticFormUsedPageTest extends TestBaseForPr {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void fillAllFieldsTest() {
        registrationPage.openPage()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setGender(GENDER)
                .setUserNumber(MOBILE)
                .setDateOfBirth(DAY_VALUE, MONTH_VALUE, YEAR_VALUE)
                .setSubject(SUBJECT)
                .setHobby(HOBBY)
                .uploadPicture(PICTURE)
                .setCurrentAddress(ADDRESS)
                .setStateAndCity(STATE, CITY)
                .submitForm()
                .checkFormTitle(SUCCESS_MESSAGE)
                .checkResult("Student Name", FIRST_NAME + " " + LAST_NAME)
                .checkResult("Student Email", EMAIL)
                .checkResult("Gender", GENDER)
                .checkResult("Mobile", MOBILE)
                .checkResult("Date of Birth", DATE_EXPECTED)
                .checkResult("Subjects", SUBJECT)
                .checkResult("Hobbies", HOBBY)
                .checkResult("Picture", PICTURE)
                .checkResult("Address", ADDRESS)
                .checkResult("State and City", STATE_CITY);
    }

    @Test
    void fillMandatoryFieldsTest() {
        registrationPage.openPage()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setGender(GENDER)
                .setUserNumber(MOBILE)
                .submitForm()
                .checkFormTitle(SUCCESS_MESSAGE)
                .checkResult("Student Name", FIRST_NAME + " " + LAST_NAME)
                .checkResult("Gender", GENDER)
                .checkResult("Mobile", MOBILE);
    }
    @Test
    void negativeSubmitWithInvalidMobileTest() {
        registrationPage.openPage()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setGender(GENDER)
                .setUserNumber("12345")
                .submitForm()
                .checkInvalidMobileValue("12345")
                .checkThatFormWasNotSubmitted();
    }
}