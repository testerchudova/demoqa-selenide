package testdata;

import static utils.RandomUtils.*;

public class TestData {
    public String
            firstName = getRandomFirstName(),
            lastName = getRandomLastName(),
            email = getRandomEmail(),
            gender = getRandomGender(),
            phone = getRandomPhone(),
            day = getRandomDay(),
            month = getRandomMonth(),
            year = getRandomYear(),
            subject = getRandomSubject(),
            hobby = getRandomHobby(),
            picture = "testpic.jpg",
            address = getRandomAddress(),
            state = getRandomState(),
            city = getRandomCity(state);
}
