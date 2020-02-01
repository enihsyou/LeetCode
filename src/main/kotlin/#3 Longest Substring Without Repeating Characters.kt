import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

class Solution3 {
    fun lengthOfLongestSubstring(s: String): Int {
        val n = s.length
        var max = 0
        val cache = mutableMapOf<Char, Int>() // <当前包含的字符, 出现的index>
        var j = 0
        var i = 0
        while (j < n) {
            val current_char = s[j]
            if (cache.containsKey(current_char)) {
/*如果在下一个字符发现了重复，可以直接跳到j的下一个，因为[i, j]都被最后两个重复字符给可惜掉了*/
                i = Integer.max(cache[current_char]!!, i)
            }
            max = Integer.max(max, j - i + 1) // +1是因为自身算一个
            cache.put(current_char, j + 1) // 如果发生了重复，下一个搜索起点在这
            j++
        }
        return max
    }

    fun lengthOfLongestSubstring2(s: String): Int {
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

            "abcabcbb".should { lengthOfLongestSubstring("abcabcbb") shouldBe 3 }
            "bbbbb".should { lengthOfLongestSubstring("bbbbb") shouldBe 1 }
            "pwwkew".should { lengthOfLongestSubstring("pwwkew") shouldBe 3 }
            "dfaergsbcsbfrehsddf".should { lengthOfLongestSubstring("dfaergsbcsbfrehsddf") shouldBe 9 }
            "tmmzuxt".should { lengthOfLongestSubstring("tmmzuxt") shouldBe 5 }
        }
    }
}
