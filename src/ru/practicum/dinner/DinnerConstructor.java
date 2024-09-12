package ru.practicum.dinner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {

    HashMap<String, ArrayList<String>> items;

    DinnerConstructor() {
        items = new HashMap<>();
    }

    void build(int numberOfCombos, ArrayList<String> selectedTypes) {
        Random random = new Random();
        int ind;
        for (int i = 1; i <= numberOfCombos; i++) {
            ArrayList<String> combination = new ArrayList<>();
            for (String selectedType : selectedTypes) {
                ArrayList<String> names = items.get(selectedType);
                ind = random.nextInt(names.size());
                combination.add(names.get(ind));
            }
            System.out.println("Комбо " + i);
            System.out.println(combination);
        }
    }

    Boolean isNotExist(String type) {
        return !items.containsKey(type);
    }
}
