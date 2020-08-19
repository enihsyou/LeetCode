import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Q1203 {

    private final CountDownLatch latch2 = new CountDownLatch(1);
    private final CountDownLatch latch3 = new CountDownLatch(1);

    public Q1203() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        latch2.countDown();
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        latch2.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        latch3.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        latch3.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}

class Q1203Test {

    private final Q1203 solution = new Q1203();

    private final ExecutorService executor = Executors.newFixedThreadPool(3);

    @ParameterizedTest
    @CsvSource({ "1, 2, 3",
                 "1, 3, 2",
                 "2, 1, 3",
                 "3, 1, 2",
                 "2, 3, 1",
                 "3, 2, 1", })
    void test(int m1, int m2, int m3) throws InterruptedException {
        try {
            long[] receptor = new long[3];
            executor.invokeAll(Arrays.asList(
                () -> dispatch(m1, receptor),
                () -> dispatch(m2, receptor),
                () -> dispatch(m3, receptor)));
            assertThat(receptor).isSorted();
        } catch (InterruptedException ignored) {}
    }

    @AfterEach
    void tearDown() {
        executor.shutdownNow();
    }

    private Void dispatch(int idx, long[] receptor) {
        try {
            switch (idx) {
                case 1:
                    solution.first(() -> receptor[0] = System.nanoTime());
                    break;
                case 2:
                    solution.second(() -> receptor[1] = System.nanoTime());
                    break;
                case 3:
                    solution.third(() -> receptor[2] = System.nanoTime());
                    break;
            }
        } catch (InterruptedException ignored) {}
        return null;
    }
}
