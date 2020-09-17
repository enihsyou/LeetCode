package leetcode.q18.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import leetcode.base.java.DiffMode;
import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/4sum/">
 * 18. 4Sum
 * </a>
 */
class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        List<List<Integer>> answer = new ArrayList<>();
        for (int point1 = 0; point1 < nums.length - 3; point1++) {
            int value1 = nums[point1];
            // 跳过重复的数字
            if (point1 > 0 && value1 == nums[point1 - 1]) {
                continue;
            }

            // 递增数列中最小的组合已经超过目标值
            if (nums[point1] + nums[point1 + 1] +
                nums[point1 + 2] + nums[point1 + 3] > target) {
                break;
            }

            // 递增数列中最大的组合还不够目标值
            if (nums[point1] + nums[nums.length - 3] +
                nums[nums.length - 2] + nums[nums.length - 1] < target) {
                continue;
            }

            for (int point2 = point1 + 1; point2 < nums.length - 2; point2++) {
                int value2 = nums[point2];
                // 跳过重复的数字
                if (point2 > point1 + 1 && value2 == nums[point2 - 1]) {
                    continue;
                }

                int target3 = target - value1 - value2;
                int point3 = point2 + 1;
                int point4 = nums.length - 1;

                // 递增数列中最小的组合已经超过目标值
                if (nums[point1] + nums[point2] +
                    nums[point3] + nums[point3 + 1] > target) {
                    break;
                }

                // 递增数列中最大的组合还不够目标值
                if (nums[point1] + nums[point2] +
                    nums[point4 - 1] + nums[point4] < target) {
                    continue;
                }

                while (point3 < point4) {
                    int value3 = nums[point3];
                    int value4 = nums[point4];

                    if (target3 == value3 + value4) {
                        List<Integer> list = new ArrayList<>();
                        list.add(value1);
                        list.add(value2);
                        list.add(value3);
                        list.add(value4);
                        answer.add(list);
                    }
                    if (target3 > value3 + value4) {
                        while (point3 < point4 && nums[point3] == value3) { point3++; }
                    } else {
                        while (point3 < point4 && nums[point4] == value4) { point4--; }
                    }
                }
            }
        }
        return answer;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected DiffMode diffMode() {
            return DiffMode.CONTAIN;
        }

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(1, 0, -1), 0, emptyLists()),
                Arguments.of(ints(1, 0, -1, 0, -2, 2), 4, emptyLists()),
                Arguments.of(ints(1, 0, -1, 0, -2, 2), 0,
                             lists(lists(-1, 0, 0, 1),
                                   lists(-2, -1, 1, 2),
                                   lists(-2, 0, 0, 2))),
                Arguments.of(ints(-3, -2, -1, 0, 1, 2, 3), 0,
                             lists(lists(-3, -2, 2, 3),
                                   lists(-3, -1, 1, 3),
                                   lists(-2, -1, 1, 2),
                                   lists(-3, 0, 1, 2),
                                   lists(-2, -1, 0, 3))),
                Arguments.of(ints(0, 0, 0, 0), 0,
                             lists(lists(0, 0, 0, 0))),
                Arguments.of(ints(-1, 0, 1, 2, -1, -4), -1,
                             lists(lists(-1, -1, 0, 1),
                                   lists(-4, 0, 1, 2))),
                Arguments.of(ints(0, 0, 0, 0, 1), 1,
                             lists(lists(0, 0, 0, 1))),
                Arguments.of(ints(0, 0, 0, 0, 1), 2, emptyLists())
            );
        }
    }
}
