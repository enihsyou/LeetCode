package leetcode.q40.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import leetcode.base.java.DiffMode;
import leetcode.base.java.ExecutionOption;
import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/combination-sum-ii/">
 * 40. Combination Sum II
 * </a>
 */
class Solution implements Cloneable {

    private final List<List<Integer>> answer = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        answer.clear();

        Arrays.sort(candidates);
        dfs(new ArrayDeque<>(candidates.length), candidates, target, 0);
        return answer;
    }

    /**
     * @param remain  还需要多少数字
     * @param pointer 接下来从哪个candidate开始选
     */
    private void dfs(Deque<Integer> stack, int[] candidates, int remain, int pointer) {
        if (remain == 0) {
            answer.add(new ArrayList<>(stack));
            return;
        }

        if (pointer == candidates.length) {
            return;
        }


        if (remain < 0) {
            return;
        }

        // 选用这个数字
        stack.addLast(candidates[pointer]);
        dfs(stack, candidates, remain - candidates[pointer], pointer + 1);

        // 不选这个数字
        stack.removeLast();
        int current = candidates[pointer];
        do {
            pointer++;
            // 跳过重复数字
        } while (pointer < candidates.length && candidates[pointer] == current);
        dfs(stack, candidates, remain, pointer);
    }


    public List<List<Integer>> combinationSum2_2(int[] candidates, int target) {
        answer.clear();

        Arrays.sort(candidates);
        dfs2(new ArrayDeque<>(candidates.length), candidates, target, 0);
        return answer;
    }

    /**
     * @param remain  还需要多少数字
     * @param pointer 接下来从哪个candidate开始选
     */
    private void dfs2(Deque<Integer> stack, int[] candidates, int remain, int pointer) {
        if (remain == 0) {
            answer.add(new ArrayList<>(stack));
            return;
        }
        for (int i = pointer; i < candidates.length; i++) {
            if (pointer != i && candidates[i - 1] == candidates[i]) {
                // 跳过重复项
                continue;
            }
            int thisRemain = remain - candidates[i];
            if (thisRemain >= 0) {
                // 选用这个数字
                stack.addLast(candidates[i]);
                dfs2(stack, candidates, thisRemain, i + 1);
                // 不选这个数字
                stack.removeLast();
            } else {
                // 超过求和限制
                break;
            }
        }
    }


    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected void tweakExecutionOption(ExecutionOption option) {
            option.diffMode(DiffMode.CONTAIN);
        }

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(1, 1, 5), 6,
                             lists(lists(1, 5))),
                Arguments.of(ints(10, 1, 2, 7, 6, 1, 5), 8,
                             lists(lists(1, 7), lists(1, 2, 5), lists(2, 6), lists(1, 1, 6))),
                Arguments.of(ints(2, 5, 2, 1, 2), 5,
                             lists(lists(1, 2, 2), lists(5)))
            );
        }
    }
}
