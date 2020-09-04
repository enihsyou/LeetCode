import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class Q268 {

    public int missingNumber(int[] nums) {
        long sum       = nums.length * (nums.length + 1) / 2;
        long actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return (int) (sum - actualSum);
    }
}


@SuppressWarnings("ZeroLengthArrayAllocation")
class Q268Test {

    private final Q268 solution = new Q268();

    @Test
    void test() {
        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(solution.missingNumber(new int[]{ 3, 0, 1 })).isEqualTo(2);
            soft.assertThat(solution.missingNumber(new int[]{ 9, 6, 4, 2, 3, 5, 7, 0, 1 })).isEqualTo(8);
            soft.assertThat(solution.missingNumber(new int[]{ })).isEqualTo(0);
        });
    }
}
