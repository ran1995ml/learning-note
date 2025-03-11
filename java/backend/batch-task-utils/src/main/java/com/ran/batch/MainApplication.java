package com.ran.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * MainApplication
 *
 * @author rwei
 * @since 2025/2/8 17:42
 */
public class MainApplication {
    public static void main(String[] args) throws InterruptedException {
        int taskSize = 60;
        List<String> taskList = generateTask(taskSize);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        long startTime = System.currentTimeMillis();
        TaskDisposeUtils.dispose(taskList, MainApplication::disposeTask, executor);
        System.out.printf("execute %s task successfully, take %s ms%n", taskSize, System.currentTimeMillis() - startTime);
        executor.shutdown();
    }

    public static List<String> generateTask(int taskSize) {
        List<String> taskList = new ArrayList<>();
        for (int i = 0; i < taskSize; i++) {
            taskList.add(String.format("task-%s", i));
        }
        return taskList;
    }

    public static void disposeTask(String task) {
        System.out.printf("execute task %s successfully%n", task);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
