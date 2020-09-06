package leetcode.q122.kotlin;

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

/**
 * 122. Best Time to Buy and Sell Stock II
 *
 * [LeetCode](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)
 */

class Solution {

    fun maxProfit(prices: IntArray): Int {
        if (prices.size < 2) {
            return 0
        }

        var profits = 0

        for (day in 1 until prices.size) {
            val day1Price = prices[day - 1]
            val day2Price = prices[day]

            if (isRise(day1Price, day2Price)) {
                /* 涨了，就有获利*/
                profits += day2Price - day1Price
            }
        }

        return profits
    }

    private fun isRise(day1: Int, day2: Int) = day1 < day2

    class SolutionTest {

        private val solution = Solution()

        @ParameterizedTest(name = "maxProfit({0}) = {1}")
        @MethodSource("provider")
        fun maxProfit(input: IntArray, output: Int) {
            Assertions.assertEquals(solution.maxProfit(input), output)
        }

        companion object {

            @JvmStatic
            fun provider(): List<Arguments> {
                return listOf(
                    Arguments.of(intArrayOf(7, 1, 5, 3, 6, 4), 7),
                    Arguments.of(intArrayOf(1, 2, 3, 4, 5), 4),
                    Arguments.of(intArrayOf(7, 6, 4, 3, 1), 0),
                    Arguments.of(intArrayOf(1), 0),
                    Arguments.of(intArrayOf(), 0)
                )
            }
        }
    }
}
