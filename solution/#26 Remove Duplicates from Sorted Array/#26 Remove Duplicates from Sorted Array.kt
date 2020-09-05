import io.kotlintest.matchers.numerics.shouldBeExactly
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

/**
 * 26. Remove Duplicates from Sorted Array
 *
 * [LeetCode](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)
 */

class Solution26 {

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

class Solution26Test : StringSpec({
    val solution = Solution26()

    "1, 1, 2"{
        val input = intArrayOf(1, 1, 2)
        solution.removeDuplicates(input) shouldBeExactly 2
        input.copyOfRange(0, 2) shouldBe intArrayOf(1, 2)
    }

    "single element"{
        val input = intArrayOf(1)
        solution.removeDuplicates(input) shouldBeExactly 1
        input shouldBe intArrayOf(1)
    }

    "no element"{
        val input = intArrayOf()
        solution.removeDuplicates(input) shouldBeExactly 0
        input shouldBe intArrayOf()
    }
})
