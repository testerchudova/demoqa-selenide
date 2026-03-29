package org.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapWithWhileLoop {
    private Map<Integer, String> map = new HashMap<>();
    private int idCounter = 1; // Уникальный ключ для записей

    // Добавление (в Map нужно передавать ключ и значение)
    public void addElement(String item) {
        map.put(idCounter++, item);
    }

    // Удаление (удаляем значения, которые совпадают с переданным)
    public void removeElement(String item) {
        map.values().remove(item);
    }

    // Поиск значения с помощью while и итератора
    public boolean searchElement(String target) {
        Iterator<String> iterator = map.values().iterator();

        while (iterator.hasNext()) { // Пока есть следующий элемент
            if (iterator.next().equals(target)) {
                return true;
            }
        }
        return false;
    }
}