package task;

import java.io.Serializable;
import java.util.List;

public class MergeSortResult<T> implements Serializable {

    private static final long serialVersionUID = 103L;

    private List<T> elements;
    private int random;

    public MergeSortResult(List<T> elements, int random) {
        this.elements = elements;
        this.random = random;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    public int getRandom() {
        return random;
    }

    public void setRandom(int random) {
        this.random = random;
    }
}
