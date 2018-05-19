import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import java.util.*;

class Q39_Combination_Sum {

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
    private void helper(final int remain, final int start) {
        if (remain == 0) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = start; i >= 0; i--) {
            /*当前选取的数字大小*/
            final int num = candidates[i];
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

    public static class SolutionTest {

        @Rule
        public final JUnitSoftAssertions soft = new JUnitSoftAssertions();

        private final Q39_Combination_Sum solution = new Q39_Combination_Sum();

        @Test
        public void test() {
            soft.assertThat(solution.combinationSum(new int[]{2, 3, 6, 7}, 7))
                .hasSameElementsAs(Arrays.asList(Arrays.asList(7), Arrays.asList(2, 2, 3)));
            soft.assertThat(solution.combinationSum(new int[]{2, 3, 5}, 8))
                .hasSameElementsAs(
                    Arrays.asList(Arrays.asList(2, 2, 2, 2), Arrays.asList(2, 3, 3), Arrays.asList(3, 5)));
        }
    }
}
