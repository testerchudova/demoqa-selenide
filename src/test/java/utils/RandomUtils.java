package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RandomUtils {

    private final Faker faker = new Faker(new Locale("en-US"));

    public String getRandomFirstName() {
        return faker.name().firstName();
    }

    public String getRandomLastName() {
        return faker.name().lastName();
    }

    public String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public String getRandomGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public String getRandomPhone() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public String getRandomDay() {
        return String.format("%02d", faker.number().numberBetween(1, 28));
    }

    public String getRandomMonth() {
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return faker.options().option(months);
    }

    public String getRandomYear() {
        return String.valueOf(faker.number().numberBetween(1900, 2100));
    }

    public String getRandomSubject() {
        String[] subjects = {"Maths", "Accounting", "Arts", "Social Studies", "Physics", "Chemistry",
                "Computer Science", "Commerce", "Economics", "Civics", "English", "Hindi", "Biology", "History"};
        return faker.options().option(subjects);
    }

    public String getRandomHobby() {
        return faker.options().option("Sports", "Reading", "Music");
    }

    public String getRandomAddress() {
        return faker.address().fullAddress();
    }

    public String getRandomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public String getRandomCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> null;
        };
    }
}