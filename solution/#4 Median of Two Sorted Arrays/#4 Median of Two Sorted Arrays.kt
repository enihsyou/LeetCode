package leetcode.q4.kotlin;

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.LinkedList

class Solution {

    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val m = nums1.size
        val n = nums2.size
        val queue1 = LinkedList<Int>(nums1.toList())
        val queue2 = LinkedList<Int>(nums2.toList())
        fun nextSmall(): Int {
            if (queue1.isNotEmpty() && queue2.isNotEmpty()) {
                val a = queue1.element()
                val b = queue2.element()
                return if (a < b) queue1.remove() else queue2.remove()
            } else if (queue1.isNotEmpty()) {
                return queue1.remove()
            } else if (queue2.isNotEmpty()) {
                return queue2.remove()
            }
            return 0
        }

        var c = 0
        while (c < (m + n + 1) / 2 - 1) {
            nextSmall()
            c++
        }
        return if ((m + n) % 2 == 0) {
            (nextSmall() + nextSmall()) / 2.0
        } else {
            nextSmall().toDouble()
        }
    }
}

class SolutionTest {

    private val solution = Solution()

    @ParameterizedTest(name = "findMedianSortedArrays({0}, {1}) = {2}")
    @MethodSource("provider")
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray, output: Double) {
        Assertions.assertEquals(solution.findMedianSortedArrays(nums1, nums2), output)
    }

    companion object {

        @JvmStatic
        fun provider(): List<Arguments> {
            return listOf(
                Arguments.of(intArrayOf(1, 3), intArrayOf(2), 2.0),
                Arguments.of(intArrayOf(1, 2), intArrayOf(3, 4), 2.5),
                Arguments.of(intArrayOf(), intArrayOf(), 0.0),
                Arguments.of(intArrayOf(1, 3, 4, 2, 5, 8, 3, 6), intArrayOf(), 3.5),
                Arguments.of(intArrayOf(1, 2, 3), intArrayOf(2), 2.0)
            )
        }
    }
}
