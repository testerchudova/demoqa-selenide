package qaguru.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import qaguru.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class JsonParsingTest {

    // Добавила ClassLoader, чтобы получать JSON файл из resources
    private final ClassLoader classLoader =
            JsonParsingTest.class.getClassLoader();

    // Создала ObjectMapper для преобразования JSON в Java-объект
    private final ObjectMapper objectMapper =
            new ObjectMapper();

    @Test
    void jsonShouldBeParsedWithJackson() throws Exception {

        // Открываю файл customer.json из resources
        try (InputStream is =
                     classLoader.getResourceAsStream("customer.json")) {

            // С помощью Jackson преобразую JSON в объект Customer
            Customer customer =
                    objectMapper.readValue(is, Customer.class);

            // Основные поля

            // Проверяю ID клиента
            Assertions.assertEquals(
                    101,
                    customer.getId()
            );

            // Проверяю имя клиента
            Assertions.assertEquals(
                    "Ekaterina Ivanova",
                    customer.getName()
            );

            // Проверяю email клиента
            Assertions.assertEquals(
                    "katya@test.com",
                    customer.getEmail()
            );

            // Проверяю, что клиент активен
            Assertions.assertTrue(
                    customer.isActive()
            );

            // ================= Адреса =================

            // Проверяю количество адресов
            Assertions.assertEquals(
                    2,
                    customer.getAddresses().size()
            );

            // Проверяю первый адрес

            // Проверяю город
            Assertions.assertEquals(
                    "Moscow",
                    customer.getAddresses().get(0).getCity()
            );

            // Проверяю улицу
            Assertions.assertEquals(
                    "Lenina",
                    customer.getAddresses().get(0).getStreet()
            );

            // Проверяю номер дома
            Assertions.assertEquals(
                    "15",
                    customer.getAddresses().get(0).getHouse()
            );

            // Проверяю номер квартиры
            Assertions.assertEquals(
                    "45",
                    customer.getAddresses().get(0).getApartment()
            );

            // Проверяю второй адрес

            Assertions.assertEquals(
                    "Saint Petersburg",
                    customer.getAddresses().get(1).getCity()
            );

            Assertions.assertEquals(
                    "Nevsky Prospect",
                    customer.getAddresses().get(1).getStreet()
            );

            Assertions.assertEquals(
                    "20",
                    customer.getAddresses().get(1).getHouse()
            );

            Assertions.assertEquals(
                    "12",
                    customer.getAddresses().get(1).getApartment()
            );

            //  Заказы

            // Проверяю количество заказов
            Assertions.assertEquals(
                    2,
                    customer.getOrders().size()
            );

            // Проверяю первый заказ

            // Проверяю название товара
            Assertions.assertEquals(
                    "Laptop",
                    customer.getOrders().get(0).getProductName()
            );

            // Проверяю количество
            Assertions.assertEquals(
                    1,
                    customer.getOrders().get(0).getQuantity()
            );

            // Проверяю цену
            Assertions.assertEquals(
                    85000,
                    customer.getOrders().get(0).getPrice()
            );

            // Проверяю второй заказ

            Assertions.assertEquals(
                    "Mouse",
                    customer.getOrders().get(1).getProductName()
            );

            Assertions.assertEquals(
                    2,
                    customer.getOrders().get(1).getQuantity()
            );

            Assertions.assertEquals(
                    1500,
                    customer.getOrders().get(1).getPrice()
            );
        }
    }
}