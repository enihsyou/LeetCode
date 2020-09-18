package leetcode.q88.java;

import java.util.stream.Stream;

import leetcode.base.java.AssertMode;
import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/merge-sorted-array/">
 * 88. Merge Sorted Array
 * </a>
 */
class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int pointerM = m - 1;
        int pointerN = n - 1;
        int pointerI = nums1.length - 1;
        while (pointerI >= 0 && pointerM >= 0 && pointerN >= 0) {
            if (nums1[pointerM] <= nums2[pointerN]) {
                nums1[pointerI--] = nums2[pointerN--];
            } else {
                nums1[pointerI--] = nums1[pointerM--];
            }
        }
        // while (pointerI >= 0 && pointerM >= 0) {
        //     nums1[pointerI--] = nums1[pointerM--];
        // }
        while (pointerI >= 0 && pointerN >= 0) {
            nums1[pointerI--] = nums2[pointerN--];
        }
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected AssertMode assertMode() {
            return AssertMode.exceptArgumentMode(0);
        }

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(1, 2, 3, 0, 0, 0), 3,
                             ints(2, 5, 6), 3,
                             ints(1, 2, 2, 3, 5, 6)),
                Arguments.of(ints(1, 2, 3, 0, 0, 0), 3,
                             ints(4, 5, 6), 3,
                             ints(1, 2, 3, 4, 5, 6)),
                Arguments.of(ints(4, 5, 6, 0, 0, 0), 3,
                             ints(1, 2, 3), 3,
                             ints(1, 2, 3, 4, 5, 6))
            );
        }
    }
}
