import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-02-01
 */
class Q22Test {

    private final Q22_Generate_Parentheses solution = new Q22_Generate_Parentheses();

    @Test
    void test() {
        SoftAssertions.assertSoftly(soft -> {

            soft.assertThat(solution.generateParenthesis(3))
                .hasSize(5)
                .hasSameElementsAs(Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()"));
        });
    }
}
