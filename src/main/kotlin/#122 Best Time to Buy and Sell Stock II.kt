@file:Suppress("NOTHING_TO_INLINE", "ControlFlowWithEmptyBody")

import io.kotlintest.matchers.numerics.shouldBeExactly
import io.kotlintest.specs.StringSpec

/**
 * 122. Best Time to Buy and Sell Stock II
 *
 * [LeetCode](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)
 */

private class Solution122 {

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
            } else if (isFall(day1Price, day2Price)) {
                /* 跌了，就卖出*/
            }
        }

        return profits
    }

    private inline fun isRise(day1: Int, day2: Int) = day1 < day2
    private inline fun isFall(day1: Int, day2: Int) = day1 > day2
}

class Solution122Test : StringSpec({
    val solution = Solution122()

    "[7,1,5,3,6,4]"{
        val input = intArrayOf(7, 1, 5, 3, 6, 4)
        solution.maxProfit(input) shouldBeExactly 7
    }

    "[1,2,3,4,5]"{
        val input = intArrayOf(1, 2, 3, 4, 5)
        solution.maxProfit(input) shouldBeExactly 4
    }

    "[7,6,4,3,1]"{
        val input = intArrayOf(7, 6, 4, 3, 1)
        solution.maxProfit(input) shouldBeExactly 0
    }

    "no element"{
        solution.maxProfit(intArrayOf()) shouldBeExactly 0
        solution.maxProfit(intArrayOf(1)) shouldBeExactly 0
    }
})
