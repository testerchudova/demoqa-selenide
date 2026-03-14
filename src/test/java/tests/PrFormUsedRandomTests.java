package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import testbase.TestBaseForPr;
import testdata.TestData;

public class PrFormUsedRandomTests extends TestBaseForPr {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void fillAllFieldsTest() {

        TestData data = new TestData();

        registrationPage.openPage()
                .removeBanners()
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setEmail(data.email)
                .setGender(data.gender)
                .setUserNumber(data.phone)
                .setDateOfBirth(data.day, data.month, data.year)
                .setSubject(data.subject)
                .setHobby(data.hobby)
                .uploadPicture(data.picture)
                .setCurrentAddress(data.address)
                .setStateAndCity(data.state, data.city)
                .submitForm()
                .checkFormTitle("Thanks for submitting the form")
                //Проверки
                .checkResult("Student Name", data.firstName + " " + data.lastName)
                .checkResult("Student Email", data.email)
                .checkResult("Gender", data.gender)
                .checkResult("Mobile", data.phone)
                .checkResult("Date of Birth", data.day + " " + data.month + "," + data.year)
                .checkResult("Subjects", data.subject)
                .checkResult("Hobbies", data.hobby)
                .checkResult("Address", data.address)
                .checkResult("State and City", data.state + " " + data.city);
    }

    @Test
    void fillMandatoryFieldsTest() {
        TestData data = new TestData();

        registrationPage.openPage()
                .removeBanners()
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setGender(data.gender)
                .setUserNumber(data.phone)
                .submitForm()
                .checkFormTitle("Thanks for submitting the form")
                .checkResult("Student Name", data.firstName + " " + data.lastName)
                .checkResult("Gender", data.gender)
                .checkResult("Mobile", data.phone);
    }

    @Test
    void negativeSubmitWithInvalidMobileTest() {

        TestData data = new TestData();

        registrationPage.openPage()
                .removeBanners()
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setGender(data.gender)
                .setUserNumber("12345")
                .submitForm()
                .checkInvalidMobileValue("12345")
                .checkThatFormWasNotSubmitted();
    }

    @Test
    void negativeSubmitWithoutFirstNameTest() {
        TestData data = new TestData();

        registrationPage.openPage()
                .removeBanners()
                // Специально пропустила шаг setFirstName(...)
                .setLastName(data.lastName)
                .setGender(data.gender)
                .setUserNumber(data.phone)
                .submitForm()
                .checkThatFormWasNotSubmitted();
    }

    @Test
    void negativeSubmitWithInvalidEmailTest() {
        TestData data = new TestData();

        registrationPage.openPage()
                .removeBanners()
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setGender(data.gender)
                .setUserNumber(data.phone)
                .setEmail("bad-email.com") // Неправильный формат почты
                .submitForm()
                .checkThatFormWasNotSubmitted();
    }
}