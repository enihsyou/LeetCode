package leetcode.q4.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/median-of-two-sorted-arrays/">
 * 4. Median of Two Sorted Arrays
 * </a>
 */
class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0.0;
        }

        int totalLength = nums1.length + nums2.length;
        if ((totalLength & 1) == 0) {
            // 偶数个，中位数是中间两数的平均值
            return (findKth(nums1, nums2, totalLength / 2) +
                    findKth(nums1, nums2, totalLength / 2 + 1)) / 2.0;
        } else {
            // 奇数个，中位数是中间的那个数
            return findKth(nums1, nums2, totalLength / 2 + 1);
        }
    }

    private static int findKth(int[] nums1, int[] nums2, int kth) {
        assert kth > 0;
        // 从nums1 nums2中排除前这么多个元素
        int exclude1 = 0, exclude2 = 0;

        while (true) {
            if (exclude1 == nums1.length) {
                return nums2[exclude2 - 1 + kth];
            }
            if (exclude2 == nums2.length) {
                return nums1[exclude1 - 1 + kth];
            }
            if (kth == 1) {
                return Math.min(nums1[exclude1], nums2[exclude2]);
            }
            int halfKth = kth / 2;
            int offset1 = Math.min(nums1.length, exclude1 + halfKth) - 1;
            int offset2 = Math.min(nums2.length, exclude2 + halfKth) - 1;

            int pivot1 = nums1[offset1];
            int pivot2 = nums2[offset2];
            if (pivot1 <= pivot2) {
                kth -= offset1 + 1 - exclude1;
                exclude1 = offset1 + 1;
            } else {
                kth -= offset2 + 1 - exclude2;
                exclude2 = offset2 + 1;
            }
        }
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(1, 3, 5, 7), ints(2, 4, 6, 8), 4.5),
                Arguments.of(ints(1, 3, 5, 7), ints(2, 4, 6, 8, 9), 5.0),
                Arguments.of(ints(1, 3), ints(2), 2.0),
                Arguments.of(ints(1, 2), ints(3, 4), 2.5),
                Arguments.of(emptyInts(), emptyInts(), 0.0),
                Arguments.of(ints(1, 2, 3, 3, 4, 5, 6, 8), emptyInts(), 3.5),
                Arguments.of(ints(1, 2, 3), ints(2), 2.0)
            );
        }
    }
}
