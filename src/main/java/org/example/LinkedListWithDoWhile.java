package org.example;
import java.util.LinkedList;

public class LinkedListWithDoWhile {
    private LinkedList<String> linkedList = new LinkedList<>();

    // Добавление
    public void addElement(String item) {
        linkedList.add(item);
    }

    // Удаление
    public void removeElement(String item) {
        linkedList.remove(item);
    }

    // Поиск элемента с помощью do-while
    public boolean searchElement(String target) {
        if (linkedList.isEmpty()) {
            return false; // Если список пуст, даже не начинаем
        }

        int index = 0;
        do {
            if (linkedList.get(index).equals(target)) {
                return true;
            }
            index++;
        } while (index < linkedList.size()); // Проверяем в конце

        return false;
    }
}