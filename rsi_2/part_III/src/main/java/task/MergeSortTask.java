package task;

import java.io.Serializable;
import java.util.List;

public class MergeSortTask implements Task, Serializable {

    private static final long serialVersionUID = 101L;

    @Override
    public Object compute(Object args) {
        MergeSortInput<Object> input = (MergeSortInput<Object>) args;

        List<Object> elements = input.getElements();
        elements.sort(null);
        return new MergeSortResult<>(elements);
    }

    @Override
    public String toString() {
        return "MergeSortTask";
    }
}
