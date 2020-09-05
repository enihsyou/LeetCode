@file:Suppress("NOTHING_TO_INLINE")

import io.kotlintest.specs.StringSpec
import org.assertj.core.api.Assertions

/**
 * 56. Merge Intervals
 *
 * [LeetCode](https://leetcode-cn.com/problems/merge-intervals/)
 */

private class Solution56 {

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

class Solution56Test : StringSpec({
    "example 1"{
        /*
        Input: [[1,3],[2,6],[8,10],[15,18]]
        Output: [[1,6],[8,10],[15,18]]
        Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
        */
        val input = arrayOf(
            intArrayOf(1, 3),
            intArrayOf(2, 6),
            intArrayOf(8, 10),
            intArrayOf(15, 18))
        val actual = Solution56().merge(input)
        val expect = arrayOf(
            intArrayOf(1, 6),
            intArrayOf(8, 10),
            intArrayOf(15, 18))

        Assertions.assertThat(actual).isEqualTo(expect)
    }
    "example 2"{
        /*
        Input: [[1,4],[4,5]]
        Output: [[1,5]]
        Explanation: Intervals [1,4] and [4,5] are considered overlapping.
        */
        val input = arrayOf(
            intArrayOf(1, 4),
            intArrayOf(4, 5))
        val actual = Solution56().merge(input)
        val expect = arrayOf(
            intArrayOf(1, 5))

        Assertions.assertThat(actual).isEqualTo(expect)
    }
})
