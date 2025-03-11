package com.ran.batch;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * TaskDisposeUtils
 *
 * @author rwei
 * @since 2025/2/8 15:18
 */
public class TaskDisposeUtils {
    public static <T> void dispose(List<T> taskList, Consumer<? super T> consumer, Executor executor) throws InterruptedException {
        if (taskList == null || taskList.size() == 0) return;

        CountDownLatch latch = new CountDownLatch(taskList.size());
        for (T task : taskList) {
            executor.execute(() -> {
                try {
                    consumer.accept(task);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
    }
}
