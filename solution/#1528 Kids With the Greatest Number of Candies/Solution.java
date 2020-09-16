package leetcode.q1528.java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies/">
 * 1431. Kids With the Greatest Number of Candies
 * </a>
 */
class Solution {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> answer = new ArrayList<>(candies.length);

        int maxCandy = 0;
        for (int candy : candies) { maxCandy = Math.max(maxCandy, candy); }
        for (int candy : candies) { answer.add(candy + extraCandies >= maxCandy); }
        return answer;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(2, 3, 5, 1, 3), 3, lists(true, true, true, false, true)),
                Arguments.of(ints(4, 2, 1, 1, 2), 1, lists(true, false, false, false, false)),
                Arguments.of(ints(12, 1, 12), 10, lists(true, false, true))
            );
        }
    }
}
