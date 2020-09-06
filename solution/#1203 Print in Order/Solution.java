package leetcode.q1203.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Solution {

    private int stage = 1;

    public Solution() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (this) {
            printFirst.run();
            stage = 2;
            this.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (this) {
            while (stage != 2) {
                this.wait();
            }
            printSecond.run();
            stage = 3;
            this.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (this) {
            while (stage != 3) {
                this.wait();
            }
            printThird.run();
            this.notifyAll();
        }
    }

    static class SolutionTest {

        private final Solution solution = new Solution();

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

}
