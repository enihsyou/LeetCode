package leetcode.q1580.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/shuffle-the-array/">
 * 1470. Shuffle the Array
 * </a>
 */
class Solution {

    public int[] shuffle(int[] nums, int n) {
        int[] answer = new int[2 * n];
        for (int i = 0; i < n; i++) {
            answer[2 * i]     = nums[i];
            answer[2 * i + 1] = nums[i + n];
        }
        return answer;
    }

    public int[] shuffle2(int[] nums, int n) {

        for (int i = 0; i < 2 * n; i++) {
            // 存放位置
            int put = i < n ? i * 2 : (i - n) * 2 + 1;
            // 低十位信息，原数字
            int low = nums[i] & 1023;
            // 原数字放到低十位，应该在这里的数字放到高十位
            nums[put] |= low << 10;
        }

        for (int i = 0; i < 2 * n; i++) {
            // 取高十位，应该在这里的数字
            nums[i] >>>= 10;
        }
        return nums;
    }

    public int[] shuffle3(int[] nums, int n) {

        for (int i = 0; i < 2 * n; i++) {
            // 是个未处理的数
            if (nums[i] > 0) {
                int j = i;
                while (nums[i] > 0) {
                    // 当前这个位置数字颖刚的存放位置
                    j = j < n ? j * 2 : (j - n) * 2 + 1;

                    // 把当前这个数字放到它正确的位置上
                    int tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                    // 标记它已经完成处理
                    nums[j] = -nums[j];
                }
            }
        }
        for (int i = 0; i < 2 * n; i++) {
            nums[i] = -nums[i];
        }
        return nums;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(2, 5, 1, 3, 4, 7), 3, ints(2, 3, 5, 4, 1, 7)),
                Arguments.of(ints(1, 2, 3, 4, 4, 3, 2, 1), 4, ints(1, 4, 2, 3, 3, 2, 4, 1)),
                Arguments.of(ints(1, 1, 2, 2), 2, ints(1, 2, 1, 2))
            );
        }
    }
}
