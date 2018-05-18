import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.forAll
import io.kotlintest.properties.headers
import io.kotlintest.properties.row
import io.kotlintest.properties.table

class Solution15 {
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

class Solution15Test : MySpec() {
    init {
        Solution15().run {
            "should" {
                val table = table(
                    headers("input", "output"),
                    row(intArrayOf(-1, 0, 1, 2, -1, -4), listOf(listOf(-1, 0, 1), listOf(-1, -1, 2)))
                )
                forAll(table) { input, output ->
                    threeSum(input).also { println(it) } shouldBe output
                }
            }
        }
    }
}
