package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== 1. ArrayList + цикл for ===");
        ListWithForLoop listClass = new ListWithForLoop();
        listClass.addElement("Apple");
        listClass.addElement("Banana");
        listClass.addElement("Orange");

        System.out.println("Ищем 'Apple' (ожидаем true): " + listClass.searchElement("Apple"));
        System.out.println("Ищем 'Kiwi' (ожидаем false): " + listClass.searchElement("Kiwi"));
        listClass.removeElement("Apple");
        System.out.println("Ищем 'Apple' после удаления (ожидаем false): " + listClass.searchElement("Apple"));

        System.out.println("\n=== 2. HashSet + цикл for-each ===");
        SetWithForEachLoop setClass = new SetWithForEachLoop();
        setClass.addElement("QA");
        setClass.addElement("Dev");

        System.out.println("Ищем 'QA' (ожидаем true): " + setClass.searchElement("QA"));
        System.out.println("Ищем 'PM' (ожидаем false): " + setClass.searchElement("PM"));

        System.out.println("\n=== 3. HashMap + цикл while ===");
        MapWithWhileLoop mapClass = new MapWithWhileLoop();
        mapClass.addElement("Selenide");
        mapClass.addElement("JUnit");

        System.out.println("Ищем 'Selenide' (ожидаем true): " + mapClass.searchElement("Selenide"));
        System.out.println("Ищем 'TestNG' (ожидаем false): " + mapClass.searchElement("TestNG"));

        System.out.println("\n=== 4. LinkedList + цикл do-while ===");
        LinkedListWithDoWhile linkedListClass = new LinkedListWithDoWhile();
        linkedListClass.addElement("Chrome");
        linkedListClass.addElement("Firefox");

        System.out.println("Ищем 'Chrome' (ожидаем true): " + linkedListClass.searchElement("Chrome"));
        System.out.println("Ищем 'Safari' (ожидаем false): " + linkedListClass.searchElement("Safari"));
    }
}