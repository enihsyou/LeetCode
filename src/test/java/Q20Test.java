import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-02-01
 */
class Q20Test {

    private final Q20 solution = new Q20();

    @Test
    void test() {
        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(solution.isValid("")).isTrue();
            soft.assertThat(solution.isValid("()")).isTrue();
            soft.assertThat(solution.isValid("()[]{}")).isTrue();
            soft.assertThat(solution.isValid("(]")).isFalse();
            soft.assertThat(solution.isValid("(((((")).isFalse();
            soft.assertThat(solution.isValid("([)]")).isFalse();
            soft.assertThat(solution.isValid("{[]}")).isTrue();
        });
    }
}
