import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class Solution1 {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        for ((index, value) in nums.withIndex()) {
            val wish = target - value
            val i = nums.indexOf(wish)
            if (i >= 0 && i != index) {
                return intArrayOf(index, i)
            }
        }

//        nums.forEachIndexed { index, i ->  nums.drop(index).find { target - i == it} }
//        val associateBy = nums.mapIndexed { index, i -> index to i }.sortedBy { it.second }
//        associateBy.forEach { first ->
//            val search = associateBy.binarySearch { target - it.second }
//            if (search == first.second)
//                return intArrayOf(first.first, search)
//        }
        return intArrayOf(-1, -1)
    }
}

class Solution1Test : StringSpec() {
    init {
        Solution1().run {
            "[2, 7, 11, 15], 9" {
                twoSum(intArrayOf(2, 7, 11, 15), 9) shouldBe intArrayOf(0, 1)
            }
            "[3, 2, 4], 6"{
                twoSum(intArrayOf(3, 2, 4), 6) shouldBe intArrayOf(1, 2)
            }
            "[3, 2, 4, 5], 6"{
                twoSum(intArrayOf(3, 2, 4, 5), 6) shouldBe intArrayOf(1, 2)
            }
            "[3, 2, 5, 4, 5], 6"{
                twoSum(intArrayOf(3, 2, 5, 4, 5), 6) shouldBe intArrayOf(1, 3)
            }
        }
    }
}
