package farmer;

import worker.Worker;
import worker.WorkerImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Farmer {

    Registry registry;
    List<Worker> workers;

    public Farmer(int port) {

        try {
            registry = LocateRegistry.getRegistry(port);
            workers = Arrays.stream(registry.list())
                    .map(worker_name -> {
                        try {
                            return (Worker) registry.lookup(worker_name);
                        } catch (Exception e) {
                            System.out.println("Not able to lookup for: " + worker_name);
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            System.out.println("Registered workers: ");

            try {
                for (Worker worker : workers) {
                    System.out.println(worker.getName());
                }
            } catch (RemoteException e) {
                System.out.println("Couldn't get name of worker");
            }


        } catch (Exception e) {
            System.out.println("Problem with getting registry on port: " + port);
            e.printStackTrace();
        }
    }
}
