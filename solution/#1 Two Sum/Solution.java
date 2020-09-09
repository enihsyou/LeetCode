package leetcode.q1.java;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

class Solution {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> visited = new HashMap<>();
        for (int i = 0, numsLength = nums.length; i < numsLength; i++) {
            int     num  = nums[i];
            Integer wish = target - num;
            if (visited.containsKey(wish)) {
                int found = visited.get(wish);
                if (i < found) {
                    return new int[]{ i, found };
                } else {
                    return new int[]{ found, i };
                }
            } else {
                visited.put(num, i);
            }
        }
        return new int[]{ -1, -1 };
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(new int[]{ 2, 7, 11, 15 }, 9, new int[]{ 0, 1 }),
                Arguments.of(new int[]{ 3, 2, 4 }, 6, new int[]{ 1, 2 }),
                Arguments.of(new int[]{ 3, 2, 4, 5 }, 6, new int[]{ 1, 2 }),
                Arguments.of(new int[]{ 3, 2, 5, 4, 5 }, 6, new int[]{ 1, 3 })
            );
        }
    }
}
