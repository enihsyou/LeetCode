package leetcode.q1.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Solution {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> visited = new HashMap<>();
        for (int i = 0, numsLength = nums.length; i < numsLength; i++) {
            int     num  = nums[i];
            Integer wish = target - num;
            if (visited.containsKey(wish)) {
                return new int[]{ i, visited.get(wish) };
            } else {
                visited.put(num, i);
            }
        }
        return new int[]{ -1, -1 };
    }

    static class SolutionTest {

        private final Solution solution = new Solution();

        @ParameterizedTest(name = "twoSum({0}, {1}) = {2}")
        @MethodSource("provider")
        void twoSum(int[] input, int target, int[] output) {
            assertThat(solution.twoSum(input, target))
                .containsOnly(output);
        }

        static Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(new int[]{ 2, 7, 11, 15 }, 9, new int[]{ 0, 1 }),
                Arguments.of(new int[]{ 3, 2, 4 }, 6, new int[]{ 1, 2 }),
                Arguments.of(new int[]{ 3, 2, 4, 5 }, 6, new int[]{ 1, 2 }),
                Arguments.of(new int[]{ 3, 2, 5, 4, 5 }, 6, new int[]{ 1, 3 })
            );
        }
    }
}
