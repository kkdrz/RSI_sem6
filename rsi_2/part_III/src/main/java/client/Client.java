package client;

import farmer.Farmer;
import worker.Worker;
import worker.WorkerImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;

public class Client {

    public static void main(String[] args) throws RemoteException {
        //args[0]: worker | farmer
        //args[1]: port
        //args[2]: worker_name

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        if ("worker".equals(args[0])) {
            new WorkerImpl(args[2], Integer.valueOf(args[1]));
        } else {
            new Farmer(Integer.valueOf(args[1]));
        }
    }
}
