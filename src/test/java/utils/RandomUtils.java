package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RandomUtils {

    private static final Faker faker = new Faker(new Locale("en-US"));

    public static String getRandomFirstName() { return faker.name().firstName(); }
    public static String getRandomLastName() { return faker.name().lastName(); }
    public static String getRandomEmail() { return faker.internet().emailAddress(); }
    public static String getRandomGender() { return faker.options().option("Male", "Female", "Other"); }
    public static String getRandomPhone() { return faker.numerify("##########"); }

    public static String getRandomDay() {
        return String.format("%02d", faker.number().numberBetween(1, 28));
    }

    public static String getRandomMonth() {
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return faker.options().option(months);
    }

    public static String getRandomYear() {
        return String.valueOf(faker.number().numberBetween(1900, 2100));
    }

    public static String getRandomSubject() {
        String[] subjects = {"Maths", "Accounting", "Arts", "Social Studies", "Physics", "Chemistry",
                "Computer Science", "Commerce", "Economics", "Civics", "English", "Hindi", "Biology", "History"};
        return faker.options().option(subjects);
    }

    public static String getRandomHobby() {
        return faker.options().option("Sports", "Reading", "Music");
    }

    public static String getRandomAddress() { return faker.address().fullAddress(); }

    public static String getRandomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public static String getRandomCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> null;
        };
    }
}