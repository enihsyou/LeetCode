import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.WordSpec

class Solution3 {
    fun lengthOfLongestSubstring(s: String): Int {
        val array = s.toCharArray()
        var max = 0
        for (index in array.indices) {
            val cache = mutableSetOf<Char>()
            var p = index
            do {
                cache += array[p]
                p++
            } while (p < array.size && !cache.contains(array[p]))
            max = Integer.max(max, p - index)
        }
        return max
    }
}

class Solution3Test : WordSpec() {
    init {
        Solution3().run {
            "abcabcbb"{ lengthOfLongestSubstring("abcabcbb") shouldBe 3 }
            "bbbbb"{ lengthOfLongestSubstring("bbbbb") shouldBe 1 }
            "pwwkew"{ lengthOfLongestSubstring("pwwkew") shouldBe 3 }
            "dfaergsbcsbfrehsddf"{ lengthOfLongestSubstring("dfaergsbcsbfrehsddf") shouldBe 9 }
        }
    }
}
