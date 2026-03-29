package org.example;
import java.util.ArrayList;
import java.util.List;

public class ListWithForLoop {
    private List<String> list = new ArrayList<>();

    // Добавление
    public void addElement(String item) {
        list.add(item);
    }

    // Удаление
    public void removeElement(String item) {
        list.remove(item);
    }

    // Поиск элемента с помощью классического for
    public boolean searchElement(String target) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(target)) {
                return true; // Нашли!
            }
        }
        return false; // Ничего не нашли
    }
}
