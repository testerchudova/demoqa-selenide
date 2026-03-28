package tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import testbase.TestBaseForPr;
import testdata.TestData;

@Tag("ui")
public class PrFormWithAllureTests extends TestBaseForPr {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    @DisplayName("Успешное заполнение всех полей формы")
        void fillAllFieldsTest() {
        TestData data = new TestData();

        Allure.step("Открыть страницу регистрации и подготовить форму", () -> {
            registrationPage.openPage()
                    .removeBanners();
        });

        Allure.step("Заполнить все поля формы валидными данными", () -> {
            registrationPage
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
                    .setStateAndCity(data.state, data.city);
        });

        Allure.step("Отправить форму", () -> {
            registrationPage.submitForm();
        });

        Allure.step("Проверить успешную отправку формы и данные в таблице результатов", () -> {
            registrationPage
                    .checkFormTitle("Thanks for submitting the form")
                    .checkResult("Student Name", data.firstName + " " + data.lastName)
                    .checkResult("Student Email", data.email)
                    .checkResult("Gender", data.gender)
                    .checkResult("Mobile", data.phone)
                    .checkResult("Date of Birth", data.day + " " + data.month + "," + data.year)
                    .checkResult("Subjects", data.subject)
                    .checkResult("Hobbies", data.hobby)
                    .checkResult("Address", data.address)
                    .checkResult("State and City", data.state + " " + data.city);
        });
    }

    @Test
    @DisplayName("Успешная отправка формы только с обязательными полями")
    void fillMandatoryFieldsTest() {
        TestData data = new TestData();

        Allure.step("Открыть страницу регистрации и подготовить форму", () -> {
            registrationPage.openPage()
                    .removeBanners();
        });

        Allure.step("Заполнить обязательные поля", () -> {
            registrationPage
                    .setFirstName(data.firstName)
                    .setLastName(data.lastName)
                    .setGender(data.gender)
                    .setUserNumber(data.phone);
        });

        Allure.step("Отправить форму", registrationPage::submitForm);

        Allure.step("Проверить успешную отправку формы", () -> {
            registrationPage
                    .checkFormTitle("Thanks for submitting the form")
                    .checkResult("Student Name", data.firstName + " " + data.lastName)
                    .checkResult("Gender", data.gender)
                    .checkResult("Mobile", data.phone);
        });
    }

    @Test
    @DisplayName("Форма не отправляется при некорректном мобильном номере")
    void negativeSubmitWithInvalidMobileTest() {
        TestData data = new TestData();

        Allure.step("Открыть страницу регистрации и подготовить форму", () -> {
            registrationPage.openPage()
                    .removeBanners();
        });

        Allure.step("Заполнить форму с некорректным мобильным номером", () -> {
            registrationPage
                    .setFirstName(data.firstName)
                    .setLastName(data.lastName)
                    .setGender(data.gender)
                    .setUserNumber("12345");
        });

        Allure.step("Попытаться отправить форму", registrationPage::submitForm);

        Allure.step("Проверить, что форма не отправилась", () -> {
            registrationPage
                    .checkInvalidMobileValue("12345")
                    .checkThatFormWasNotSubmitted();
        });
    }

    @Test
    @DisplayName("Форма не отправляется без имени")
    void negativeSubmitWithoutFirstNameTest() {
        TestData data = new TestData();

        Allure.step("Открыть страницу регистрации и подготовить форму", () -> {
            registrationPage.openPage()
                    .removeBanners();
        });

        Allure.step("Заполнить форму без имени", () -> {
            registrationPage
                    .setLastName(data.lastName)
                    .setGender(data.gender)
                    .setUserNumber(data.phone);
        });

        Allure.step("Попытаться отправить форму", registrationPage::submitForm);

        Allure.step("Проверить, что форма не была отправлена", () -> {
            registrationPage.checkThatFormWasNotSubmitted();
        });
    }

    @Test
    @DisplayName("Форма не отправляется при некорректном email")
    void negativeSubmitWithInvalidEmailTest() {
        TestData data = new TestData();

        Allure.step("Открыть страницу регистрации и подготовить форму", () -> {
            registrationPage.openPage()
                    .removeBanners();
        });

        Allure.step("Заполнить форму с некорректным email", () -> {
            registrationPage
                    .setFirstName(data.firstName)
                    .setLastName(data.lastName)
                    .setGender(data.gender)
                    .setUserNumber(data.phone)
                    .setEmail("bad-email.com");
        });

        Allure.step("Попытаться отправить форму", registrationPage::submitForm);

        Allure.step("Проверить, что форма не была отправлена", () -> {
            registrationPage.checkThatFormWasNotSubmitted();
        });
    }
}
