package farmer;

import task.MergeSortInput;
import task.MergeSortResult;
import task.MergeSortTask;
import worker.Worker;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class Farmer {

    private Registry registry;
    private List<Worker> workers;

    public Farmer(int port, int randomListSize) {

        try {
            registry = LocateRegistry.getRegistry(port);
            workers = findWorkers();
            printWorkers();

            List<MyCustomObject> randomList = generateMyRandomList(randomListSize);

            List<MergeSortInput<MyCustomObject>> workersInputs = splitIn(workers.size(), randomList);
            List<MergeSortResult> workersResults = callWorkers(workersInputs);
            List<MyCustomObject> sorted = mergeResults(unpackResults(workersResults));
            sorted.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Problem with getting registry on port: " + port);
            e.printStackTrace();
        }
    }

    private void printWorkers() {
        System.out.println("Registered workers: ");

        try {
            for (Worker worker : workers) {
                System.out.println(worker.getName());
            }
        } catch (RemoteException e) {
            System.out.println("Couldn't get name of worker");
        }
    }

    private List<Worker> findWorkers() {
        List<Worker> workers = new ArrayList<>();
        try {
            for (String worker_name : registry.list()) {
                workers.add((Worker) registry.lookup(worker_name));
            }
        } catch (Exception e) {
            System.out.println("Not able to lookup for some worker");
            e.printStackTrace();
        }
        return workers;
    }

    private List<List<MyCustomObject>> unpackResults(List<MergeSortResult> results) {
        List<List<MyCustomObject>> result = new ArrayList<>();
        results.forEach(obj -> result.add(obj.getElements()));
        return result;
    }

    private List<MyCustomObject> mergeResults(List<List<MyCustomObject>> results) {
        if (results.size() == 1) return results.get(0);

        List<List<MyCustomObject>> mergedLists = new ArrayList<>();

        for (int i = 0; i < results.size(); i += 2) {
            mergedLists.add(mergeLists(results.get(i), i+1 < results.size() ? results.get(i + 1) : null));
        }
        return mergeResults(mergedLists);
    }

    private List<MyCustomObject> mergeLists(
            List<MyCustomObject> list1,
            List<MyCustomObject> list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        List<MyCustomObject> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i).compareTo(list2.get(j)) < 0) {
                result.add(list1.get(i++));
            } else {
                result.add(list2.get(j++));
            }
        }

        while (i < list1.size()) {
            result.add(list1.get(i++));
        }

        while (j < list2.size()) {
            result.add(list2.get(j++));
        }
        return result;
    }

    private List<MergeSortResult> callWorkers(List<MergeSortInput<MyCustomObject>> inputs) throws RemoteException {
        List<MergeSortResult> results = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
            MergeSortInput<MyCustomObject> current = inputs.get(i);
            results.add((MergeSortResult) workers.get(i).compute(new MergeSortTask(), current));
        }
        return results;
    }

    private List<MergeSortInput<MyCustomObject>> splitIn(int numberOfWorkers, List<MyCustomObject> el) {
        List<MergeSortInput<MyCustomObject>> partitions = new ArrayList<>();
        int partitionSize = el.size() / numberOfWorkers;
        for (int i = 0; i < el.size(); i += partitionSize) {
            if (partitions.size() == numberOfWorkers - 1) {
                partitions.add(new MergeSortInput<>(new ArrayList<>(el.subList(i, el.size()))));
                break;
            } else {
                partitions.add(new MergeSortInput<>(new ArrayList<>(el.subList(i, Math.min(i + partitionSize, el.size())))));
            }
        }
        return partitions;
    }

    private List<MyCustomObject> generateMyRandomList(int size) {
        List<String> signs = Arrays.asList("a", "b", "c", "hehe", "ad", "aaa", "bbb", "ccc", "mo", "zz", "sdfs");
        List<MyCustomObject> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(new MyCustomObject(signs.get(new Random().nextInt(signs.size())), new Random().nextInt(size)));
        }
        return result;
    }
}
