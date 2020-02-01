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
