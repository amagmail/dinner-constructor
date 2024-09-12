package ru.practicum.dinner;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String type = scanner.nextLine();

        System.out.println("Введите название блюда:");
        String name = scanner.nextLine();

        if (dc.isNotExist(type)) {
            dc.items.put(type, new ArrayList<>());
        }
        ArrayList<String> names = dc.items.get(type);
        if (!names.contains(name)) {
            names.add(name);
        }
        dc.items.put(type, names);
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед из доступных блюд:");
        System.out.println(dc.items);
        if (dc.items.isEmpty()) {
            System.out.println("ERROR: Список блюд не может быть пустым. Пожалуйста добавьте новые блюда!");
            return;
        }

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        if (!scanner.hasNextInt()) {
            System.out.println("ERROR: Необходимо ввести числовое значение!");
            scanner.nextLine();
            return;
        }
        int numberOfCombos = scanner.nextInt();
        while (numberOfCombos <= 0) {
            System.out.println("WARNING: Пожалуйста введите положительное число!");
            numberOfCombos = scanner.nextInt();
        }
        scanner.nextLine();

        ArrayList<String> selectedTypes = new ArrayList<>();
        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem = scanner.nextLine();
        while (!nextItem.isEmpty()) {
            if (dc.isNotExist(nextItem)) {
                System.out.println("WARNING: Нет такого типа блюд. Пожалуйста введите другой!");
            } else {
                selectedTypes.add(nextItem);
            }
            nextItem = scanner.nextLine();
        }
        dc.build(numberOfCombos, selectedTypes);
    }
}
