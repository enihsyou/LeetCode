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
