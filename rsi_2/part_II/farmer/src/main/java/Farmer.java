import java.rmi.RemoteException;
import java.util.List;

public class Farmer {
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException();
        }

        Worker worker1;
        Worker worker2;
        try {
            worker1 = (Worker) java.rmi.Naming.lookup(args[0]);
            worker2 = (Worker) java.rmi.Naming.lookup(args[1]);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        PrimeNumbersTask task = new PrimeNumbersTask();
        PrimeNumbersInputType input1 = new PrimeNumbersInputType(2L, 1000L);
        PrimeNumbersInputType input2 = new PrimeNumbersInputType(10001L, 2000L);
        PrimeNumbersResultType result1;
        PrimeNumbersResultType result2;
        try {
            result1 = (PrimeNumbersResultType) worker1.compute(task, input1);
            result2 = (PrimeNumbersResultType) worker2.compute(task, input2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        }

        List<Long> result = result1.getResult();
        result.addAll(result2.getResult());
        System.out.println(result.toString());
        System.out.println(result.size());
    }
}
