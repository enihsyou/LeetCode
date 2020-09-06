package leetcode.q26.kotlin;

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

/**
 * 26. Remove Duplicates from Sorted Array
 *
 * [LeetCode](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)
 */

class Solution {

    fun removeDuplicates(nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }

        /** 不重复数字的数量，也当作位置指针使用 */
        var uniques = 1

        for (i in 1 until nums.size) {
            val num = nums[i]

            val lastUnique = nums[uniques - 1] // uniques - 1 := 上一个不重复数字的位置
            if (lastUnique == num) {
                /* 跳过重复数字 */
            } else {
                uniques++
                nums[uniques - 1] = num // uniques - 1 := 下一个不重复数字的位置
            }
        }

        return uniques
    }
}

class SolutionTest {

    private val solution = Solution()

    @ParameterizedTest(name = "removeDuplicates({0}) = {1}")
    @MethodSource("provider")
    fun removeDuplicates(input: IntArray, output: Int) {
        Assertions.assertEquals(solution.removeDuplicates(input), output)
//        Assertions.assertEquals(input.copyOfRange(0, 2), intArrayOf(1, 2))
    }

    companion object {

        @JvmStatic
        fun provider(): List<Arguments> {
            return listOf(
                Arguments.of(intArrayOf(1, 1, 2), 2),
                Arguments.of(intArrayOf(1), 1),
                Arguments.of(intArrayOf(), 0)
            )
        }
    }
}
