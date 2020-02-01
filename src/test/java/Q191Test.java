import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-02-01
 */
public class Q191Test {

    private final Q191 solution = new Q191();

    @Test
    public void test() {
        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(solution.hammingWeight(11)).isEqualTo(3);
            soft.assertThat(solution.hammingWeight(128)).isEqualTo(1);
            soft.assertThat(solution.hammingWeight(0)).isEqualTo(0);
            soft.assertThat(solution.hammingWeight(-1)).isEqualTo(32);
        });
    }
}
