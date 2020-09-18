package leetcode.q1185.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/find-in-mountain-array/">
 * 1095. Find in Mountain Array
 * </a>
 */

class Solution {

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peak = findPeakInMountainArray(mountainArr);
        int left = findValueInAscendingMountain(mountainArr, target, 0, peak);
        int stop = findValueInDescendingMountain(mountainArr, target, peak, mountainArr.length() - 1);
        return left == -1 ? stop : left;
    }

    private int findPeakInMountainArray(MountainArray mountainArr) {
        int start = 0, right = mountainArr.length() - 1;
        while (start <= right) { // break when start = right + 1
            int middle  = (start + right) / 2;
            int vMiddle = mountainArr.get(middle);
            int vRight  = mountainArr.get(middle + 1);
            if (vMiddle < vRight) { // middle落在上坡
                start = middle + 1; // peak在middle和right之间
            } else {                // middle落在下坡
                right = middle - 1; // peak在start和middle之间
            }
        }
        assert start == right + 1;
        return start;
    }

    private int findValueInAscendingMountain(MountainArray mountainArr, int target, int start, int right) {
        while (start <= right) {
            int middle  = (start + right) / 2;
            int vMiddle = mountainArr.get(middle);
            if (vMiddle < target) {
                start = middle + 1;
                continue;
            }
            if (vMiddle > target) {
                right = middle - 1;
                continue;
            }
            return middle;
        }
        return -1;
    }

    private int findValueInDescendingMountain(MountainArray mountainArr, int target, int start, int right) {
        while (start <= right) {
            int middle  = (start + right) / 2;
            int vMiddle = mountainArr.get(middle);
            if (vMiddle < target) {
                right = middle - 1;
                continue;
            }
            if (vMiddle > target) {
                start = middle + 1;
                continue;
            }
            return middle;
        }
        return -1;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(3, mountain(1, 2, 3, 4, 5, 3, 1), 2),
                Arguments.of(1, mountain(1, 5, 4, 1), 0),
                Arguments.of(5, mountain(1, 5, 4, 1), 1),
                Arguments.of(4, mountain(1, 5, 4, 1), 2),
                Arguments.of(4, mountain(1, 4, 5, 1), 1),
                Arguments.of(5, mountain(1, 4, 5, 1), 2),
                Arguments.of(3, mountain(0, 1, 2, 4, 2, 1), -1),
                Arguments.of(0, mountain(3, 5, 3, 2, 0), 4)
            );
        }

        private static MountainArray mountain(int... ints) {
            return new MountainArrayImpl(ints);
        }
    }

    interface MountainArray {

        int get(int index);

        int length();
    }

    static class MountainArrayImpl implements MountainArray {

        private final int[] array;

        private int access;

        private MountainArrayImpl(int[] array) {
            this.array = array;
        }

        @Override
        public int get(int index) {
            access++;
            assert access < 100 : "access limit exceeded.";
            return array[index];
        }

        @Override
        public int length() {
            return array.length;
        }
    }
}
