import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-02-01
 */
public class Q53Test {

    private final Q53 solution = new Q53();

    @Test
    public void test() {
        SoftAssertions.assertSoftly(soft -> {
          soft.assertThat(solution.maxSubArray(new int[]{-2, 1})).isEqualTo(1);
          soft.assertThat(solution.maxSubArray(new int[]{-2, -1})).isEqualTo(-1);
          soft.assertThat(solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})).isEqualTo(6);
        });
    }
}
