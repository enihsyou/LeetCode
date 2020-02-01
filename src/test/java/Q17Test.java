import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-02-01
 */
class Q17Test {

    private final Q17 solution = new Q17();

    @Test
    void test() {
        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(solution.letterCombinations(""))
                .hasSameElementsAs(Collections.emptyList());
            soft.assertThat(solution.letterCombinations("23"))
                .hasSameElementsAs(Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
        });
    }
}
