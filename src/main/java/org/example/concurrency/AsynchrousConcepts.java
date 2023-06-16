package org.example.concurrency;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


/**
 * Inspired from this video
 * https://www.youtube.com/watch?v=ImtZgX1nmr8
 */
public class AsynchrousConcepts {

    public static record ServiceData(List<String> data) {
        @Override
        public String toString() {
            return "ServiceData{" +
                    "data=" + data +
                    '}';
        }
    }

    static final Consumer<String> log = (m) -> {
        System.out.println(Thread.currentThread()+" "+m);
    };

    static final Consumer<ServiceData> serviceDataSink = (m) -> {
        log.accept(Thread.currentThread()+" "+m);
    };

    static final Function<ServiceData, ServiceData> enhanceData = (sd) -> {
        try {
            return new Service().enhanceData(sd);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };

    static final Supplier<ServiceData> supplier = () -> {
        try {
            return new Service().getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };

    static class Service {
        ServiceData getData() throws Exception {
            log.accept("Fetching data...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(0,10)*1000);
            log.accept("Completed fetching data!");
               return new ServiceData(List.of(UUID.randomUUID().toString()+"|Fetched"));
        }

        ServiceData enhanceData(ServiceData in) throws Exception {
            log.accept("Enhancing data...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(0,3)*1000);
            log.accept("Completed enhancing data...");
            return new ServiceData(in.data().stream().map(d -> d +"|Enhanced").toList());
        }
    }

    public void blockingFetch() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i=0; i<10; i++) {
            Future<ServiceData> future = es.submit(supplier::get);
            ServiceData sd = future.get();
            log.accept("Service Data Ready "+sd);
        }
        log.accept("\nCompleted executing tasks.");
        es.shutdown();
    }

    /**
     * This method demonstrates concurrent execution of tasks and the main thread waits
     * until the last task completion and then returns.
     */
    public void nonBlockingFetch() {
        log.accept("Submitting tasks...");
        List<CompletableFuture<ServiceData>> futures = new ArrayList<>();
        for (int i=0; i<10; i++) {
            CompletableFuture<ServiceData> completable = CompletableFuture.supplyAsync(supplier);
            completable.thenAccept(serviceDataSink);
            futures.add(completable);
        }
        log.accept("\nCompleted submitting tasks.\n");
        CompletableFuture<Void> allDone =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        allDone.thenAccept((v) -> {
            log.accept("Completed processing all tasks.");
        }).join(); // .join() makes the main thread wait until the .thenAccept future completion.
    }


    public void nonBlockingWorkflow() {
        log.accept("Nonblocking workflow submitting tasks...");
        List<CompletableFuture<ServiceData>> futures = new ArrayList<>();
        for (int i=0; i<10; i++) {
            CompletableFuture completable =
                    CompletableFuture.supplyAsync(supplier)
                            .thenApplyAsync(enhanceData)
                            .thenAccept(serviceDataSink);
            futures.add(completable);
        }
        log.accept("\nCompleted Nonblocking submitting tasks.\n");
        CompletableFuture<Void> allDone =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        allDone.thenAccept((v) -> {
            log.accept("Completed workflow tasks...");
        }).join(); // .join() makes the main thread wait until the .thenAccept future completion.
    }

    public static void main(String[] args) throws Exception {
        //new AsynchrousConcepts().blockingFetch();
        //new AsynchrousConcepts().nonBlockingFetch();
        new AsynchrousConcepts().nonBlockingWorkflow();
    }
}
