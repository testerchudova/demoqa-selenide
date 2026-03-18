package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.RegistrationPage;
import testbase.TestBaseForPr;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("Параметризованные тесты формы регистрации")
public class PrFormParameterizedTests extends TestBaseForPr {

    RegistrationPage registrationPage = new RegistrationPage();

    private static final String SUCCESS_MESSAGE = "Thanks for submitting the form";

    static Stream<org.junit.jupiter.params.provider.Arguments> mandatoryFieldsData() {
        // Добавила MethodSource для передачи нескольких наборов данных
        // в позитивный тест с обязательными полями.
        return Stream.of(
                arguments("Anna", "Ivanova", "Female", "1234567890"),
                arguments("Petr", "Sidorov", "Male", "9876543210"),
                arguments("Maria", "Petrova", "Female", "1112223344")
        );
    }

    @Tag("SMOKE")
    @MethodSource("mandatoryFieldsData")
    @ParameterizedTest(name = "Успешная отправка обязательных полей для: {0} {1}, пол: {2}, телефон: {3}")
    @DisplayName("Успешная отправка формы с обязательными полями")
    void fillMandatoryFieldsParameterizedTest(String firstName, String lastName, String gender, String phone) {
        // Сделала параметризованный позитивный тест через MethodSource.
        // Проверила, что форма успешно отправляется для каждого набора данных.
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(phone)
                .submitForm()
                .checkFormTitle(SUCCESS_MESSAGE)
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phone);
    }

    @Tag("SMOKE")
    @CsvFileSource(
            resources = "/registrationFullData.csv",
            numLinesToSkip = 1
    )
    @ParameterizedTest(name = "Успешная отправка полной формы для пользователя: {0} {1}")
    @DisplayName("Успешная отправка формы со всеми полями из CSV-файла")
    void fillAllFieldsFromCsvFileTest(String firstName,
                                      String lastName,
                                      String email,
                                      String gender,
                                      String phone,
                                      String day,
                                      String month,
                                      String year,
                                      String subject,
                                      String hobby,
                                      String picture,
                                      String address,
                                      String state,
                                      String city) {

        // Добавила CsvFileSource, чтобы хранить тестовые данные отдельно от кода.
        // Проверила, что каждая строка из CSV запускается как отдельный прогон теста.
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(phone)
                .setDateOfBirth(day, month, year)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadPicture(picture)
                .setCurrentAddress(address)
                .setStateAndCity(state, city)
                .submitForm()
                .checkFormTitle(SUCCESS_MESSAGE)
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phone)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);
    }

    @ValueSource(strings = {
            "12345",      // меньше 10 символов
            "999",
            "abcdefghij"
            // "123456789011" // больше 10 символов: кейс исключила, потому что поле ограничивает ввод
    })
    @Tag("NEGATIVE")
    @ParameterizedTest(name = "Форма не должна отправляться с невалидным телефоном: {0}")
    @DisplayName("Проверка отправки формы с невалидными телефонами")
    void negativeSubmitWithInvalidMobileTest(String invalidPhone) {
        // Добавила ValueSource для набора невалидных телефонов.
        // Проверила, что форма не отправляется для каждого значения.
        registrationPage.openPage()
                .removeBanners()
                .setFirstName("Anna")
                .setLastName("Ivanova")
                .setGender("Female")
                .setUserNumber(invalidPhone)
                .submitForm()
                .checkInvalidMobileValue(invalidPhone)
                .checkThatFormWasNotSubmitted();
    }
    @SuppressWarnings("unused")
    @CsvSource(value = {
            "bad-email.com, email без @",
            "test@, email без домена",
            "@mail.com, email без имени",
            "'test mail@mail.com', email с пробелом"
    })
    @Tag("NEGATIVE")
    @ParameterizedTest(name = "Форма не должна отправляться с невалидным email: {0} ({1})")
    @DisplayName("Проверка отправки формы с невалидными email")
    void negativeSubmitWithInvalidEmailTest(String invalidEmail, String description) {
        // Добавила CsvSource для передачи самого email и описания кейса.
        // Проверила, что форма не отправляется для каждого невалидного email.
        registrationPage.openPage()
                .removeBanners()
                .setFirstName("Anna")
                .setLastName("Ivanova")
                .setGender("Female")
                .setUserNumber("1234567890")
                .setEmail(invalidEmail)
                .submitForm()
                .checkThatFormWasNotSubmitted();
    }
}