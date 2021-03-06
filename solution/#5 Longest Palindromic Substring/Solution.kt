package leetcode.q5.kotlin;

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Solution {

    fun longestPalindrome(s: String): String {
        val T = preProcess(s)
        println(T)
        val P = IntArray(T.length)
        var C = 0
        var R = 0
        for (i in 1 until T.length - 1) {
            val i_mirror = 2 * C - i
            P[i] = if (R > i) minOf(R - i, P[i_mirror]) else 0

            while (T[i + 1 + P[i]] == T[i - 1 - P[i]])
                P[i]++

            if (i + P[i] > R) {
                C = i
                R = i + P[i]
            }
        }
        var maxLen = 0
        var centerIndex = 0
        for (i in 1 until P.size - 1) {
            if (P[i] > maxLen) {
                maxLen = P[i]
                centerIndex = i
            }
        }
        println("maxLen = ${maxLen}")
        println("centerIndex = ${centerIndex}")
        return s.substring((centerIndex - 1 - maxLen) / 2, (centerIndex - 1 - maxLen) / 2 + maxLen)
    }

    private fun preProcess(s: String) =
        s.toCharArray().joinToString(separator = "#", prefix = "^#", postfix = "#$")
}

class SolutionTest {

    private val solution = Solution()

    @ParameterizedTest(name = "removeDuplicates({0}) = {1}")
    @MethodSource("provider")
    fun removeDuplicates(input: String, output: String) {
        Assertions.assertEquals(solution.longestPalindrome(input), output)
    }

    companion object {

        @JvmStatic
        fun provider(): List<Arguments> {
            return listOf(
                Arguments.of("babad", "bab"),
                Arguments.of("cbbd", "bb"),
                Arguments.of("", ""),
                Arguments.of("a", "a")
            )
        }
    }
}
