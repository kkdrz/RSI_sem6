package worker;

import task.Task;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class WorkerImpl extends UnicastRemoteObject implements Worker {

    private String name;

    public WorkerImpl(String name, int port) throws RemoteException {
        super();
        this.name = name;

        Registry registry;

        try {
            registry = LocateRegistry.createRegistry(port);
            System.out.println("Registry didn't exist, created new one");
        } catch (RemoteException re) {
            System.out.println("Remote registry exist");
            registry = LocateRegistry.getRegistry(port);
        } catch (Exception e) {
            System.out.println("Worker \"" + name + "\" couldn't be rebinded.");
            e.printStackTrace();
            return;
        }
        registry.rebind(name, this);
        System.out.println("Worker: " + name + " is binded.");
    }

    @Override
    public Object compute(Task task, Object params) {
        return task.compute(params);
    }

    @Override
    public String getName() {
        return name;
    }

}
