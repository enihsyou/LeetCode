import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Q22 {

    private List<String> result;

    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>(n);

        StringBuilder builder = new StringBuilder(n);

        addParentheses(builder, 0, n);

        return result;
    }

    /**
     * @param lefts   多少未闭合的左括号在builder里
     * @param remains 还需要添加多少左括号
     */
    private void addParentheses(final StringBuilder builder, final int lefts, final int remains) {
        if (lefts == 0 && remains == 0){
            result.add(builder.toString());
            return;
        }

        /*当前循环字符的添加位置*/
        final int position = builder.length();

        /*如果左边有未闭合的左括号*/
        if (lefts > 0) {
            /*添加右括号*/
            builder.append(')');
            addParentheses(builder, lefts - 1, remains);
            builder.deleteCharAt(position);
        }
        if (remains > 0) {
            builder.append('(');
            addParentheses(builder, lefts + 1, remains - 1);
            builder.deleteCharAt(position);
        }
    }

}

class Q22Test {

    private final Q22 solution = new Q22();

    @Test
    void test() {
        SoftAssertions.assertSoftly(soft -> {

            soft.assertThat(solution.generateParenthesis(3))
                .hasSize(5)
                .hasSameElementsAs(Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()"));
        });
    }
}
