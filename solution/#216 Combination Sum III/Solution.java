package leetcode.q216.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/combination-sum-iii/">
 * 216. Combination Sum III
 * </a>
 */
class Solution {

    private static final int UPPER = 9;

    private List<List<Integer>> answer;

    private boolean[] stack;

    public List<List<Integer>> combinationSum3(int k, int n) {
        answer = new ArrayList<>(k * k);
        stack  = new boolean[UPPER + 1];

        int least = k * (k + 1) / 2;
        if (least > n) {
            // k个数的组合至少加出这么多，不满足n的总数要求
            return Collections.emptyList();
        }

        dfs(1, n, k);
        return answer;
    }

    /**
     * @param startNum   从这个数开始选
     * @param sumRemain  还剩多少可以加的
     * @param slotRemain 还剩多少的空需要填充元素
     */
    private void dfs(int startNum, int sumRemain, int slotRemain) {
        if (sumRemain == 0 && slotRemain == 0) {
            answer.add(toList(stack));
            return;
        }

        if (slotRemain == 0 || startNum > UPPER) {
            // 已经用尽了空位但结果和不对 说明这条路走不通了
            return;
        }

        int pretendTake = sumRemain - startNum;
        if (pretendTake < 0) {
            // 当前数字已经过限，以后更大的数字肯定也是
            return;
        }
        // 选了当前数
        stack[startNum] = true;
        dfs(startNum + 1, pretendTake, slotRemain - 1);
        // 没选当前数
        stack[startNum] = false;
        dfs(startNum + 1, sumRemain, slotRemain);
    }

    private static List<Integer> toList(boolean[] ints) {
        List<Integer> integers = new ArrayList<>(ints.length);
        for (int i = 1; i < ints.length; i++) {
            // skip index 0
            if (ints[i]) {
                integers.add(i);
            }
        }
        return integers;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(3, 7, lists(lists(1, 2, 4))),
                Arguments.of(3, 9, lists(lists(1, 2, 6), lists(1, 3, 5), lists(2, 3, 4))),
                Arguments.of(3, 15, lists(lists(1, 5, 9), lists(1, 6, 8), lists(2, 4, 9),
                                          lists(2, 5, 8), lists(2, 6, 7), lists(3, 4, 8),
                                          lists(3, 5, 7), lists(4, 5, 6))),
                Arguments.of(2, 18, lists()),
                Arguments.of(5, 20)
            );
        }
    }
}
