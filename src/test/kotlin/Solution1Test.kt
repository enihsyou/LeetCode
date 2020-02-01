import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

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
