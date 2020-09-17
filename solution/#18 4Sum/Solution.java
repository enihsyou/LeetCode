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
            if (point1 > 0 && value1 == nums[point1 - 1]) continue;

            for (int point2 = point1 + 1; point2 < nums.length - 2; point2++) {
                int value2 = nums[point2];
                // 跳过重复的数字
                if (point2 > point1 + 1 && value2 == nums[point2 - 1]) continue;

                int target3 = target - value1 - value2;

                int point3 = point2 + 1;
                int point4 = nums.length - 1;
                while (point3 < point4) {
                    int value3 = nums[point3];
                    int value4 = nums[point4];

                    if (target3 == value3 + value4) {
                        answer.add(result(value1, value2, value3, value4));
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

    private static List<Integer> result(int a, int b, int c, int d) {
        List<Integer> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        return list;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected DiffMode diffMode() {
            return DiffMode.CONTAIN;
        }

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(-2, -1, 0, 0, 1, 2), 0,
                             lists(lists(-1, 0, 0, 1),
                                   lists(-2, -1, 1, 2),
                                   lists(-2, 0, 0, 2))),
                Arguments.of(ints(1, 0, -1, 0, -2, 2), 0,
                             lists(lists(-1, 0, 0, 1),
                                   lists(-2, -1, 1, 2),
                                   lists(-2, 0, 0, 2))),
                Arguments.of(ints(0, 0, 0, 0), 0,
                             lists(lists(0, 0, 0, 0))),
                Arguments.of(ints(0, 0, 0, 0, 1), 1,
                             lists(lists(0, 0, 0, 1))),
                Arguments.of(ints(0, 0, 0, 0, 1), 2,
                             emptyLists())
            );
        }
    }
}
