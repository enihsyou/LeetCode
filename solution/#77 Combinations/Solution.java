package leetcode.q77.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;

import leetcode.base.java.DiffMode;
import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/combinations/">
 * 77. Combinations
 * </a>
 */
class Solution {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>(k * k);

        Deque<Integer> stack = new ArrayDeque<>(n);
        dfs(answer, stack, n, k, 1);

        return answer;
    }

    /**
     * @param maxNum     从from..n选数
     * @param minNum     从from..n选数
     * @param totalCount 组成k个元素
     */
    private static void dfs(List<List<Integer>> answer, Deque<Integer> stack,
                            int maxNum, int totalCount, int minNum) {
        if (stack.size() == totalCount) {
            answer.add(new ArrayList<>(stack));
            return;
        }

        int slotRemains = totalCount - stack.size() - 1;
        int upper       = maxNum - slotRemains;
        for (int i = minNum; i <= upper; i++) {
            stack.addLast(i);
            dfs(answer, stack, maxNum, totalCount, i + 1);
            stack.removeLast();
        }
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected DiffMode diffMode() {
            return DiffMode.CONTAIN;
        }

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(4, 2, lists(lists(2, 4), lists(3, 4), lists(2, 3),
                                         lists(1, 2), lists(1, 3), lists(1, 4)))
            );
        }
    }
}
