package testdata;

import utils.RandomUtils;

public class TestData {

    RandomUtils random = new RandomUtils();
    public String
            firstName = random.getRandomFirstName(),
            lastName = random.getRandomLastName(),
            email = random.getRandomEmail(),
            gender = random.getRandomGender(),
            phone = random.getRandomPhone(),
            day = random.getRandomDay(),
            month = random.getRandomMonth(),
            year = random.getRandomYear(),
            subject = random.getRandomSubject(),
            hobby = random.getRandomHobby(),
            picture = "testpic.jpg",
            address = random.getRandomAddress(),
            state = random.getRandomState(),
            city = random.getRandomCity(state);
}
