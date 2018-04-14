import java.io.Serializable;

public class InputType implements Serializable {
    private static final long serialVersionUID = 101L;
    public double x1;
    public double x2;
    String operation;

    public double getx1() {
        return x1;
    }

    public double getx2() {
        return x2;
    }
}
