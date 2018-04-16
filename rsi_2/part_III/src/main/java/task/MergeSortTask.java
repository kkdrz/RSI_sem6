package task;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class MergeSortTask implements Task, Serializable {

    private static final long serialVersionUID = 101L;

    @Override
    public Object compute(Object args) {
        MergeSortInput<Object> input = (MergeSortInput<Object>) args;

        List<Object> elements = input.getElements();
        elements.sort(null);

        int rand = new Random(input.getRandom()).nextInt(100);
        System.out.println("Losowa liczba: " + rand);
        return new MergeSortResult<>(elements, rand);
    }

    @Override
    public String toString() {
        return "MergeSortTask";
    }
}
