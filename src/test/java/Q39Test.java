import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-02-01
 */
public class Q39Test {

    private final Q39 solution = new Q39();

    @Test
    public void test() {
        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(solution.combinationSum(new int[]{2, 3, 6, 7}, 7))
                .hasSameElementsAs(Arrays.asList(Arrays.asList(7), Arrays.asList(2, 2, 3)));
            soft.assertThat(solution.combinationSum(new int[]{2, 3, 5}, 8))
                .hasSameElementsAs(
                    Arrays.asList(Arrays.asList(2, 2, 2, 2), Arrays.asList(2, 3, 3), Arrays.asList(3, 5)));
        });
    }
}
