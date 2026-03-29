package org.example;
import java.util.HashSet;
import java.util.Set;

public class SetWithForEachLoop {
    private Set<String> set = new HashSet<>();

    // Добавление
    public void addElement(String item) {
        set.add(item);
    }

    // Удаление
    public void removeElement(String item) {
        set.remove(item);
    }

    // Поиск элемента с помощью for-each
    public boolean searchElement(String target) {
        for (String element : set) {
            if (element.equals(target)) {
                return true;
            }
        }
        return false;
    }
}
