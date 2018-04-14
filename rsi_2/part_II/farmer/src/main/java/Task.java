import java.io.Serializable;

public interface Task extends Serializable {
    Object compute(Object params);
}
