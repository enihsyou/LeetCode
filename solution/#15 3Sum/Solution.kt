package leetcode.q15.kotlin;

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Solution {

    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()

        val result = mutableSetOf<List<Int>>()

        for (i in 0 until nums.size) {
            var left = i + 1
            var right = nums.size - 1
            val current = nums[i]
            val target = -current
            while (left < right) {
                val left_value = nums[left]
                val right_value = nums[right]
                val sum = left_value + right_value
                when {
                    sum == target -> {
                        result += listOf(current, left_value, right_value)
                        while (left < right && nums[left] == left_value) left++
                        while (right > i && nums[right] == right_value) right--
                    }

                    sum < target  -> left++
                    sum > target  -> right--
                }
            }
        }

        return result.toList()
    }
}

class SolutionTest {

    private val solution = Solution()

    @ParameterizedTest(name = "threeSum({0}) = {1}")
    @MethodSource("provider")
    fun threeSum(input: IntArray, output: List<List<Int>>) {
        assertThat(solution.threeSum(input))
            .containsExactlyInAnyOrderElementsOf(output)
    }

    companion object {

        @JvmStatic
        fun provider(): List<Arguments> {
            return listOf(
                Arguments.of(intArrayOf(-1, 0, 1, 2, -1, -4), listOf(listOf(-1, 0, 1), listOf(-1, -1, 2)))
            )
        }
    }
}
