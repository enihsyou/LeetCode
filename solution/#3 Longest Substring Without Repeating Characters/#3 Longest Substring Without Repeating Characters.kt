package leetcode.q3.kotlin;

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Solution {

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

class SolutionTest {

    private val solution = Solution()

    @ParameterizedTest(name = "lengthOfLongestSubstring({0}) = {1}")
    @MethodSource("provider")
    fun lengthOfLongestSubstring(input: String, output: Int) {
        Assertions.assertEquals(solution.lengthOfLongestSubstring(input), output)
    }

    companion object {

        @JvmStatic
        fun provider(): List<Arguments> {
            return listOf(
                Arguments.of("abcabcbb", 3),
                Arguments.of("bbbbb", 1),
                Arguments.of("pwwkew", 3),
                Arguments.of("dfaergsbcsbfrehsddf", 9),
                Arguments.of("tmmzuxt", 5)
            )
        }
    }
}
