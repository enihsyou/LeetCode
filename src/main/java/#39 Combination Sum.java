import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


/**
 * #39 Combination Sum
 */
class Q39 {

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

}
