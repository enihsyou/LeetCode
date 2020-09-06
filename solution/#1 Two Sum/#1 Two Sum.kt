package leetcode.q1.kotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Solution {

    fun twoSum(nums: IntArray, target: Int): IntArray {
        val cache = mutableMapOf<Int, Int>()
        nums.forEachIndexed { index, i ->
            val wish = target - i
            if (cache.contains(wish))
                return intArrayOf(index, cache[wish]!!)
            cache[i] = index
        }

        return intArrayOf(-1, -1)
    }

    fun twoSum2(nums: IntArray, target: Int): IntArray {
        for ((index, value) in nums.withIndex()) {
            val wish = target - value
            val i = nums.indexOf(wish)
            if (i >= 0 && i != index) {
                return intArrayOf(index, i)
            }
        }

        return intArrayOf(-1, -1)
    }

    class SolutionTest {

        private val solution = Solution()

        @ParameterizedTest(name = "twoSum({0}, {1}) = {2}")
        @MethodSource("provider")
        fun twoSum(input: IntArray, target: Int, output: IntArray) {
            assertThat(solution.twoSum(input, target))
                .containsOnly(*output)
        }

        @ParameterizedTest(name = "twoSum2({0}, {1}) = {2}")
        @MethodSource("provider")
        fun twoSum2(input: IntArray, target: Int, output: IntArray) {
            assertThat(solution.twoSum2(input, target))
                .containsOnly(*output)
        }

        companion object {

            @JvmStatic
            fun provider(): List<Arguments> {
                return listOf(
                    Arguments.of(intArrayOf(2, 7, 11, 15), 9, intArrayOf(0, 1)),
                    Arguments.of(intArrayOf(3, 2, 4), 6, intArrayOf(1, 2)),
                    Arguments.of(intArrayOf(3, 2, 4, 5), 6, intArrayOf(1, 2)),
                    Arguments.of(intArrayOf(3, 2, 5, 4, 5), 6, intArrayOf(1, 3))
                )
            }
        }
    }
}
