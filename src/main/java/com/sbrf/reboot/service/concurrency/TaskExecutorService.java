package com.sbrf.reboot.service.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutorService {

    public static final int OPTIMAL_NUMBER = Runtime.getRuntime().availableProcessors() + 1;

    private final int numberOfThreads;

    private final ExecutorService service;

    public TaskExecutorService(int numberOfThreads) {
        quantification(numberOfThreads);
        this.service = Executors.newFixedThreadPool(numberOfThreads);
        this.numberOfThreads = numberOfThreads;
    }

    public void execute(Task task) {
        for (int i = 0; i != this.numberOfThreads; i++) {
            this.service.execute(task);
        }
    }

    public void shutdown() {
        service.shutdown();
    }

    private static void quantification(int numberOfThreads) {
        if (numberOfThreads <= 0) {
            throw new IllegalArgumentException("Negative or zero thread count");
        } else if (numberOfThreads > OPTIMAL_NUMBER) {
            throw new IllegalArgumentException("Not optimal number of threads");
        }
    }

}
