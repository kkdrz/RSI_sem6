package edu.pwr.drozd.rsi_5.dao;

import edu.pwr.drozd.rsi_5.entity.Fruit;

import java.util.HashSet;

public class FruitDAO {

    HashSet<Fruit> inMemoryFruits = createInMemoryDB();

    public Fruit getByName(String name) {
        if (name == null) {
            return null;
        }

        for (Fruit f : inMemoryFruits) {
            if (f.getName().toLowerCase().equals(name)) {
                return f;
            }
        }

        return null;
    }

    public boolean addFruit(Fruit fruit) {
        if (fruit == null) {
            return false;
        }

        return inMemoryFruits.add(fruit);
    }

    public boolean removeFruit(Fruit fruit) {
        if (fruit == null) {
            return false;
        }

        return inMemoryFruits.remove(fruit);
    }

    public boolean updateFruit(Fruit fruit) {
        inMemoryFruits.remove(fruit);
        return inMemoryFruits.add(fruit);
    }

    public static HashSet<Fruit> createInMemoryDB() {
        HashSet<Fruit> set = new HashSet<>();
        set.add(new Fruit("Strawberry", 12.32, 8));
        set.add(new Fruit("Apple", 12.32, 7));
        set.add(new Fruit("Mango", 12.32, 9));
        set.add(new Fruit("Blackberry", 12.32, 5));
        set.add(new Fruit("Banana", 12.32, 7));
        set.add(new Fruit("Watermelon", 12.32, 7));
        set.add(new Fruit("Kiwifruit", 12.32, 5));
        return set;
    }

    public HashSet<Fruit> getAll() {
        return inMemoryFruits;
    }
}
