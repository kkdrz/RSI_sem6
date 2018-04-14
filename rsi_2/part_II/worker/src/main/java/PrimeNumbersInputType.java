import java.io.Serializable;

class PrimeNumbersInputType implements Serializable {
    private static final long serialVersionUID = 102L;

    private long firstNumber;
    private long lastNumber;

    PrimeNumbersInputType(long firstNumber, long lastNumber) {
        if (firstNumber <= 1 || firstNumber > lastNumber) throw new IllegalArgumentException();
        this.firstNumber = firstNumber;
        this.lastNumber = lastNumber;
    }

    long getFirstNumber() {
        return firstNumber;
    }

    long getLastNumber() {
        return lastNumber;
    }
}
