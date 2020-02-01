/**
 * #53 Maximum Subarray
 */
class Q53 {

    public int maxSubArray(int[] nums) {
        if (nums.length == 0) throw new IllegalArgumentException();

        int max = nums[0];

        /*num[i] = 末尾在这里的序列和 最大值*/
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i], nums[i - 1] + nums[i]);
            max     = Math.max(max, nums[i]);
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {
        if (nums.length == 0) throw new IllegalArgumentException();

        int max = nums[0];
        int cur = 0;
        int temp_sum = 0;
        while (cur < nums.length) {
            /*当前指向的数字*/
            final int current = nums[cur];
            /*尝试累加到计算中的序列中*/
            final int temp_add = temp_sum + current;
            /*更新最大值*/
            if (temp_add > max) max = temp_add;
            /*如果大小小于0了，不需要它*/
            if (temp_add < 0) {
                cur++;
                temp_sum = 0;
                continue;
            }
            temp_sum = temp_add;
            cur++;
        }
        return max;
    }

}
