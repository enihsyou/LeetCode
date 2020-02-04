@file:Suppress("NOTHING_TO_INLINE")

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

        var purchaseDay = -1 // -1代表未持有
        var day1Price = prices[0]
        for (day in 1 until prices.size) {
            val day2Price = prices[day]

            if (isFall(day1Price, day2Price) && isHolding(purchaseDay)) {
                /* 已购入，跌了，卖出*/
                profits += day1Price - prices[purchaseDay]
                purchaseDay = -1
            } else if (isRise(day1Price, day2Price) && !isHolding(purchaseDay)) {
                /* 未购入，涨了，买进*/
                purchaseDay = day - 1
            }
            day1Price = day2Price
        }

        if (purchaseDay >= 0) {
            profits += day1Price - prices[purchaseDay]
        }
        return profits
    }

    private inline fun isRise(day1: Int, day2: Int) = day1 < day2
    private inline fun isFall(day1: Int, day2: Int) = day1 > day2
    private inline fun isHolding(purchaseDay: Int) = purchaseDay >= 0
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
