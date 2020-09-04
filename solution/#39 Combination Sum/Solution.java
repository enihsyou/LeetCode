import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


/**
 * #39 Combination Sum
 */
class Solution {

    private Deque<Integer> stack;

    private List<List<Integer>> result;

    private int[] candidates;


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        result = new ArrayList<>();
        stack = new LinkedList<>();
        Arrays.sort(candidates);
        helper(target, candidates.length - 1);
        return result;
    }

    /**
     * @param remain 还需要多少数字
     * @param start  接下来从哪个candidate开始选
     */
    private void helper(int remain, int start) {
        if (remain == 0) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = start; i >= 0; i--) {
            /*当前选取的数字大小*/
            int num = candidates[i];
            /*可以添加当前数字*/
            if (remain >= num) {
                /*添加当前数字*/
                stack.push(Integer.valueOf(num));
                helper(remain - num, i);
                /*递归跳出，移除当前数字*/
                stack.pop();
            }
        }
    }

    static class SolutionTest {

        private final Solution solution = new Solution();

        @Test
        void test() {
            SoftAssertions.assertSoftly(soft -> {
                soft.assertThat(solution.combinationSum(new int[]{2, 3, 6, 7}, 7))
                    .hasSameElementsAs(Arrays.asList(Arrays.asList(7), Arrays.asList(2, 2, 3)));
                soft.assertThat(solution.combinationSum(new int[]{2, 3, 5}, 8))
                    .hasSameElementsAs(
                        Arrays.asList(Arrays.asList(2, 2, 2, 2), Arrays.asList(2, 3, 3), Arrays.asList(3, 5)));
            });
        }
    }
}
