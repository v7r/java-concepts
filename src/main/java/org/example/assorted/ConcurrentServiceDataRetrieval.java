package org.example.assorted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 *  Write a sample java program. Given a List of Services retrieve the data from each service
 *  concurrently and collect the data into a shared list. Return this shared list after
 *  all the retrieval tasks are complete. ?
 *
 *  This was asked in Comcast interview for IC4 level.
 *
 */
class Service {
    private String name;

    public Service(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String fetchData() {
        // Simulate fetching data from the service
        try {
            Thread.sleep(2000); // Simulating some delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Data from " + name;
    }
}

public class ConcurrentServiceDataRetrieval {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Service> services = Arrays.asList(
                new Service("Service1"),
                new Service("Service2"),
                new Service("Service3")
        );

        List<String> sharedList = retrieveDataFromServices(services);

        // Print the collected data
        for (String data : sharedList) {
            System.out.println(data);
        }
    }

    public static List<String> retrieveDataFromServices(List<Service> services)
            throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(services.size());

        List<CompletableFuture<String>> futures = new ArrayList<>();

        // Submit tasks to retrieve data from each service
        for (Service service : services) {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(service::fetchData, executor);
            futures.add(future);
        }

        // Wait for all tasks to complete and collect the results
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        CompletableFuture<List<String>> result = allFutures.thenApply(v ->
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
        );

        List<String> collectedData = result.get();

        // Shutdown the executor
        executor.shutdown();

        return collectedData;
    }
}

