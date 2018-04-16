package task;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class MergeSortInput<T> implements Serializable {

    private static final long serialVersionUID = 102L;

    private List<T> elements;
    private int random;

    public MergeSortInput(List<T> elements) {
        this.elements = elements;
        this.random = new Random().nextInt();
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
