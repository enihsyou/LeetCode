package leetcode.q350.java;

import java.util.Arrays;
import java.util.stream.Stream;

import leetcode.base.java.DiffMode;
import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

class Solution {

    public int[] intersect(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] answer = new int[Math.min(length1, length2)];

        int point1 = 0, point2 = 0, point0 = 0;
        while (point1 < length1 && point2 < length2) {
            int ele1 = nums1[point1];
            int ele2 = nums2[point2];
            if (ele1 == ele2) {
                point1++;
                point2++;
                answer[point0++] = ele1;
            } else if (ele1 < ele2) {
                point1++;
            } else {
                point2++;
            }
        }

        if (answer.length == point0) {
            return answer;
        } else {
            int[] copy = new int[point0];
            System.arraycopy(answer, 0, copy, 0, point0);
            return copy;
        }
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected DiffMode diffMode() {
            return DiffMode.CONTAIN;
        }

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(1, 2, 2, 1), ints(2, 2), ints(2, 2)),
                Arguments.of(ints(4, 9, 5), ints(9, 4, 9, 8, 4), ints(4, 9)),
                Arguments.of(ints(1, 1, 1, 1), ints(4, 4, 3, 1), ints(1)),
                Arguments.of(ints(1, 2, 3), ints(3, 2, 1), ints(1, 2, 3)),
                Arguments.of(emptyInts(), ints(1), emptyInts())
            );
        }
    }
}
