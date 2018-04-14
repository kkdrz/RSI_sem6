import java.util.LinkedList;
import java.util.List;

public class PrimeNumbersTask implements Task {
    private static final long serialVersionUID = 101L;

    @Override
    public PrimeNumbersResultType compute(Object params) {
        PrimeNumbersInputType primeNumbersInputType = (PrimeNumbersInputType) params;
        long firstNumber = primeNumbersInputType.getFirstNumber();
        long lastNumber = primeNumbersInputType.getLastNumber();
        List<Long> result = new LinkedList<>();
        for (long i = firstNumber; i <= lastNumber; i++) {
            boolean isPrime = true;
            for (long j = 2; isPrime && j < i; j++) {
                if (i % j == 0) isPrime = false;
            }
            if (isPrime) result.add(i);
        }
        return new PrimeNumbersResultType(result);
    }
}
