import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class Solution1 {
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
}

class Solution1Test : StringSpec() {
    init {
        Solution1().run {
            "[2, 7, 11, 15], 9" {
                twoSum(intArrayOf(2, 7, 11, 15), 9).sortedArray() shouldBe intArrayOf(0, 1)
            }
            "[3, 2, 4], 6"{
                twoSum(intArrayOf(3, 2, 4), 6).sortedArray() shouldBe intArrayOf(1, 2)
            }
            "[3, 2, 4, 5], 6"{
                twoSum(intArrayOf(3, 2, 4, 5), 6).sortedArray() shouldBe intArrayOf(1, 2)
            }
            "[3, 2, 5, 4, 5], 6"{
                twoSum(intArrayOf(3, 2, 5, 4, 5), 6).sortedArray() shouldBe intArrayOf(1, 3)
            }
        }
    }
}
