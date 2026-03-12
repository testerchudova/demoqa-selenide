package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import testbase.TestBaseForPr;

import static testdata.TestDataOne.*;

public class PracticFormUsedPageTest extends TestBaseForPr {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void fillAllFieldsTest() {
        registrationPage.openPage()
                .removeBanners()
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
                .checkFormTitle(SUCCESS_MESSAGE);

        registrationPage.getResultsTable().checkResult("Student Name", FIRST_NAME + " " + LAST_NAME);
        registrationPage.getResultsTable().checkResult("Student Email", EMAIL);
        registrationPage.getResultsTable().checkResult("Gender", GENDER);
        registrationPage.getResultsTable().checkResult("Mobile", MOBILE);
        registrationPage.getResultsTable().checkResult("Date of Birth", DATE_EXPECTED);
        registrationPage.getResultsTable().checkResult("Subjects", SUBJECT);
        registrationPage.getResultsTable().checkResult("Hobbies", HOBBY);
        registrationPage.getResultsTable().checkResult("Picture", PICTURE);
        registrationPage.getResultsTable().checkResult("Address", ADDRESS);
        registrationPage.getResultsTable().checkResult("State and City", STATE_CITY);
    }
    @Test
    void fillMandatoryFieldsTest() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setGender(GENDER)
                .setUserNumber(MOBILE)
                .submitForm()
                .checkFormTitle(SUCCESS_MESSAGE);

        registrationPage.getResultsTable().checkResult("Student Name", FIRST_NAME + " " + LAST_NAME);
        registrationPage.getResultsTable().checkResult("Gender", GENDER);
        registrationPage.getResultsTable().checkResult("Mobile", MOBILE);
    }

    @Test
    void negativeSubmitWithInvalidMobileTest() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setGender(GENDER)
                .setUserNumber("12345")
                .submitForm()
                .checkInvalidMobileValue("12345")
                .checkThatFormWasNotSubmitted();
    }
}