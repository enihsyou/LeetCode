package leetcode.q1028.kotlin;

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * #986 Interval List Intersections
 *
 * [LeetCode](https://leetcode-cn.com/problems/interval-list-intersections/)
 */

class Solution {

    fun intervalIntersection(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
        val result = mutableListOf<IntArray>()

        /** pointer of current position on A array */
        var pointerA = 0

        /** pointer of current position on B array */
        var pointerB = 0

        while (pointerA < A.size && pointerB < B.size) {
            val (startA, endA) = A[pointerA]
            val (startB, endB) = B[pointerB]

            val segmentStart = maxOf(startA, startB)
            val segmentEnd = minOf(endA, endB)

            if (segmentStart <= segmentEnd) {
                result += intArrayOf(segmentStart, segmentEnd)
            }

            if (endA < endB) {
                /* an A segment endpoint is reached */
                pointerA++
            } else {
                /* an B segment endpoint is reached */
                pointerB++
            }
        }

        return result.toTypedArray()
    }

}

class SolutionTest{

    @Test
    fun `simple example`() {
        val a = arrayOf(
            intArrayOf(0, 2),
            intArrayOf(5, 10),
            intArrayOf(13, 23),
            intArrayOf(24, 25))
        val b = arrayOf(
            intArrayOf(1, 5),
            intArrayOf(8, 12),
            intArrayOf(15, 24),
            intArrayOf(25, 26))
        val actual = Solution().intervalIntersection(a, b)
        val expect = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(5, 5),
            intArrayOf(8, 10),
            intArrayOf(15, 23),
            intArrayOf(24, 24),
            intArrayOf(25, 25))

        assertThat(actual).isEqualTo(expect)
    }
}
