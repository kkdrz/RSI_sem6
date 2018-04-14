package task;

import java.io.Serializable;
import java.util.List;

public class MergeSortInput<T> implements Serializable {

    private static final long serialVersionUID = 102L;

    private List<T> elements;

    public MergeSortInput(List<T> elements) {
        this.elements = elements;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }
}
