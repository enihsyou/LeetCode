package leetcode.q882.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/">
 * 852. Peak Index in a Mountain Array
 * </a>
 */
class Solution {

    public int peakIndexInMountainArray(int[] arr) {
        int start = 0, right = arr.length - 1;
        while (start <= right) {
            int middle = (start + right) / 2;

            assert 0 < middle && middle < arr.length;
            if (arr[middle - 1] < arr[middle] && arr[middle] > arr[middle + 1]) {
                return middle;
            } else if (arr[middle - 1] < arr[middle]) {
                start = middle + 1;
            } else if (arr[middle] > arr[middle + 1]) {
                right = middle;
            }
        }
        throw new IllegalArgumentException();
    }

    public int peakIndexInMountainArray2(int[] arr) {
        int start = 0, right = arr.length - 1;
        while (start < right) { // break when start = right
            int middle = (start + right) / 2;

            if (arr[middle] < arr[middle + 1]) {
                start = middle + 1;
            } else {
                right = middle;
            }
        }
        return right;
    }

    public int peakIndexInMountainArray3(int[] arr) {
        int start = 0, right = arr.length - 1;
        while (start <= right) {
            int middle = (start + right) / 2;

            if (arr[middle] < arr[middle + 1]) {
                start = middle + 1;
            } else if (arr[middle - 1] > arr[middle]) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(0, 1, 0), 1),
                Arguments.of(ints(0, 2, 1, 0), 1),
                Arguments.of(ints(0, 1, 2, 1, 0), 2),
                Arguments.of(ints(0, 1, 2, 3, 0), 3),
                Arguments.of(ints(3, 5, 3, 2, 0), 1),
                Arguments.of(ints(3, 4, 5, 0), 2),
                Arguments.of(ints(3, 4, 5, 6, 0), 3)
            );
        }
    }
}
