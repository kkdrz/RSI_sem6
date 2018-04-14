import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Worker extends Remote {
    Object compute(Task task, Object params) throws RemoteException;
}
