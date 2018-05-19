import kotlin.collections.EmptyList;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Q17_Letter_Combinations_of_a_Phone_Number {

    private List<String> result;

    private char[] chars;

    private String original;

    private static String change(int i) {
        switch (i) {
            case 2: return "abc";
            case 3: return "def";
            case 4: return "ghi";
            case 5: return "jkl";
            case 6: return "mno";
            case 7: return "pqrs";
            case 8: return "tuv";
            case 9: return "wxyz";
            default: return "";
        }
    }

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return Collections.emptyList();
        result = new ArrayList<>(1024);
        chars = new char[digits.length()];
        original = digits;
        add(0);

        return result;
    }

    private void add(final int cursor) {
        /*长度满足则添加到答案中*/
        if (cursor == original.length()) {
            result.add(new String(chars));
            return;
        }

        final char c = original.charAt(cursor);
        final int digit = Character.digit(c, 10);
        final String next = change(digit);
        /*循环当前数字对应的字母*/
        for (int i = 0; i < next.length(); i++) {
            final char next_char = next.charAt(i);
            chars[cursor] = next_char;
            add(cursor + 1);
        }
    }

    public static class SolutionTest {

        @Rule
        public final JUnitSoftAssertions soft = new JUnitSoftAssertions();

        private final Q17_Letter_Combinations_of_a_Phone_Number solution =
            new Q17_Letter_Combinations_of_a_Phone_Number();

        @Test
        public void test() {
            soft.assertThat(solution.letterCombinations(""))
                .hasSameElementsAs(Collections.emptyList());
            soft.assertThat(solution.letterCombinations("23"))
                .hasSameElementsAs(Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
        }
    }
}
