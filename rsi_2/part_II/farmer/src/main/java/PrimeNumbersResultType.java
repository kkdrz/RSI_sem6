import java.io.Serializable;
import java.util.List;

class PrimeNumbersResultType implements Serializable {
    private static final long serialVersionUID = 103L;

    private List<Long> result;

    PrimeNumbersResultType(List<Long> result) {
        this.result = result;
    }

    List<Long> getResult() {
        return result;
    }
}
