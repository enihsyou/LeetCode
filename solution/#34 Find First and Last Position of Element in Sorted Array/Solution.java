package leetcode.q34.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/">
 * 34. Find First and Last Position of Element in Sorted Array
 * </a>
 */
class Solution {

    public int[] searchRange(int[] nums, int target) {
        return new int[]{ leftBound(nums, target),
                          rightBound(nums, target) };
    }

    private static int leftBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // 搜索区间为 [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                // 搜索区间变为 [mid+1, right]
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 搜索区间变为 [left, mid-1]
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 收缩右侧边界
                right = mid - 1;
            }
        }
        // 检查出界情况
        if (left >= nums.length || nums[left] != target) {
            return -1;
        } else {
            return left;
        }
    }

    private static int rightBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 这里改成收缩左侧边界即可
                left = mid + 1;
            }
        }
        // 这里改为检查 right 越界的情况
        if (right < 0 || nums[right] != target) {
            return -1;
        } else {
            return right;
        }
    }


    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(5, 7, 7, 8, 8, 10), 10, ints(5, 5)),
                Arguments.of(ints(5, 7, 7, 8, 8, 10), 8, ints(3, 4)),
                Arguments.of(ints(5, 7, 7, 8, 8, 10), 7, ints(1, 2)),
                Arguments.of(ints(5, 7, 7, 8, 8, 10), 6, ints(-1, -1)),
                Arguments.of(ints(5, 7, 7, 8, 8, 10), 5, ints(0, 0)),
                Arguments.of(ints(5, 7, 7, 8, 8, 10), 4, ints(-1, -1)),
                Arguments.of(ints(0, 0, 1, 1, 2, 2, 2, 2, 3, 3,
                                  4, 4, 4, 5, 6, 6, 6, 7, 8, 8), 4, ints(10, 12))
            );
        }
    }
}
