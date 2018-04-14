package task;

import java.io.Serializable;
import java.util.List;

public class MergeSortResult<T> implements Serializable {

    private static final long serialVersionUID = 103L;

    private List<T> elements;

    public MergeSortResult(List<T> elements) {
        this.elements = elements;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }
}
