package leetcode.q39.java;

import leetcode.base.java.DiffMode;
import leetcode.base.java.ExecutionOption;
import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;


/**
 * #39 Combination Sum
 */
class Solution {

    private Deque<Integer> stack;

    private List<List<Integer>> result;

    private int[] candidates;


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        this.result     = new ArrayList<>();
        this.stack      = new ArrayDeque<>(target / 2);
        dfs(target, 0);
        return result;
    }

    /**
     * @param remain  还需要多少数字
     * @param pointer 接下来从哪个candidate开始选
     */
    private void dfs(int remain, int pointer) {
        if (remain == 0) {
            result.add(new ArrayList<>(stack));
            return;
        }

        if (pointer == candidates.length) {
            return;
        }

        if (remain < 0) {
            return;
        }

        // 重复使用这个数字
        stack.addLast(candidates[pointer]);
        dfs(remain - candidates[pointer], pointer);

        // 不选这个数字
        stack.removeLast();
        dfs(remain, pointer + 1);
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected void tweakExecutionOption(ExecutionOption option) {
            option.diffMode(DiffMode.CONTAIN);
        }

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(2, 3, 6, 7), 7,
                             lists(lists(7), lists(2, 2, 3))),
                Arguments.of(ints(2, 3, 5), 8,
                             lists(lists(2, 2, 2, 2), lists(2, 3, 3), lists(3, 5)))
            );
        }
    }
}
