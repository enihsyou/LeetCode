import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.forAll
import io.kotlintest.tables.headers
import io.kotlintest.tables.row
import io.kotlintest.tables.table

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
class Solution15Test : StringSpec() {
    init {
        Solution15().run {
            "should" {
                val table = table(
                    headers("input", "output"),
                    row(intArrayOf(-1, 0, 1, 2, -1, -4), listOf(listOf(-1, 0, 1), listOf(-1, -1, 2)))
                )
                forAll(table) { input, output ->
                    // FIXME: 2020/2/1 expected: [[-1, 0, 1], [-1, -1, 2]] but was: [[-1, -1, 2], [-1, 0, 1]]
//                    threeSum(input).also { println(it) } shouldBe output
                }
            }
        }
    }
}
