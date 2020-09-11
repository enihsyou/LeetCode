package leetcode.q1044.java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

class Solution {

    public List<String> commonChars(String[] A) {
        assert A.length > 0;
        int[] totalOccurrences = new int[26];
        int[] wordsOccurrences = new int[26];

        for (char c : A[0].toCharArray()) {
            int placement = (int) c - (int) 'a';
            totalOccurrences[placement]++;
        }

        for (int i = 1; i < A.length; i++) {
            for (char c : A[i].toCharArray()) {
                int placement = (int) c - (int) 'a';
                wordsOccurrences[placement]++;
            }
            for (int j = 0; j < wordsOccurrences.length; j++) {
                totalOccurrences[j] = Math.min(wordsOccurrences[j], totalOccurrences[j]);
                wordsOccurrences[j] = 0;
            }
        }

        List<String> answer = new ArrayList<>(26);
        for (int i = 0; i < totalOccurrences.length; i++) {
            for (int j = 0; j < totalOccurrences[i]; j++) {
                answer.add(String.valueOf((char) (i + (int) 'a')));
            }
        }
        return answer;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(strings("bella", "label", "roller"), lists("e", "l", "l")),
                Arguments.of(strings("cool", "lock", "cook"), lists("c", "o"))
            );
        }
    }
}
