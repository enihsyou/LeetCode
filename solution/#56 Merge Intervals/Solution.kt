package leetcode.q56.kotlin;

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

/**
 * 56. Merge Intervals
 *
 * [LeetCode](https://leetcode-cn.com/problems/merge-intervals/)
 */

class Solution {

    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val result = java.util.LinkedList<IntArray>()

        if (intervals.isEmpty()) {
            return emptyArray()
        } else {
            intervals.sortBy { it.start /* 按区间起始值排序 */ }
            result += intervals.first()
        }

        for (currInterval in intervals) {
            val lastInterval = result.last()
            if (lastInterval.end < currInterval.start) {
                /* 如果当前区间和上一个区间没有重叠部分，说明这是一个新的区间 */
                result += currInterval
            } else {
                /* 如果当前区间和上一个区间存在重叠部分，那么这个区间可能会被拓宽 */
                lastInterval.end = maxOf(lastInterval.end, currInterval.end)
            }
        }

        return result.toTypedArray()
    }

    var IntArray.start: Int
        inline get() = this[0]
        inline set(value) {
            this[0] = value
        }
    var IntArray.end: Int
        inline get() = this[1]
        inline set(value) {
            this[1] = value
        }

}

class SolutionTest {

    private val solution = Solution()

    @ParameterizedTest(name = "merge({0}) = {1}")
    @MethodSource("provider")
    fun merge(input: Array<IntArray>, output: Array<IntArray>) {
        Assertions.assertArrayEquals(solution.merge(input), output)
    }

    companion object {

        @JvmStatic
        fun provider(): List<Arguments> {
            return listOf(
                Arguments.of(arrayOf(
                    intArrayOf(1, 3),
                    intArrayOf(2, 6),
                    intArrayOf(8, 10),
                    intArrayOf(15, 18)), arrayOf(
                    intArrayOf(1, 6),
                    intArrayOf(8, 10),
                    intArrayOf(15, 18))),
                Arguments.of(arrayOf(
                    intArrayOf(1, 4),
                    intArrayOf(4, 5)), arrayOf(
                    intArrayOf(1, 5)))
            )
        }
    }
}
