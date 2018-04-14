import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

public class WorkerImpl extends UnicastRemoteObject implements Worker {
    private WorkerImpl() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            LocateRegistry.createRegistry(1099);
        } catch (ExportException e) {
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        }

        try {
            WorkerImpl worker = new WorkerImpl();
            java.rmi.Naming.rebind(args[0], worker);
            System.out.println("Worker is registered");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object compute(Task task, Object params) throws RemoteException {
        System.out.println("Computing...");
        Object result = task.compute(params);
        System.out.println("Finished");
        return result;
    }
}
