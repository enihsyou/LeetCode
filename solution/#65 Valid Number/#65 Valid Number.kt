package leetcode.q65.kotlin;

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Solution {

    operator fun Regex.contains(text: CharSequence): Boolean = this.matches(text)

    fun isNumber(s: String): Boolean {
        val trim = s.trim()
        val split = trim.split("[eE]".toRegex())
        val real_matcher = "[-+]?\\d+\\.\\d*|[-+]?\\d*\\.\\d+".toRegex()
        val integer_matcher = "[-+]?\\d+".toRegex()
        when {
            split.size == 2 -> {
                when (split[0]) {
                    in integer_matcher -> {
                    }

                    in real_matcher    -> {
                    }

                    else               -> return false
                }
                when (split[1]) {
                    in integer_matcher -> {
                    }

                    else               -> return false
                }
            }

            split.size == 1 -> {
                when (split[0]) {
                    in integer_matcher -> {
                    }

                    in real_matcher -> {
                    }

                    else               -> return false
                }
            }

            else            -> return false
        }
        return true
    }

    class SolutionTest {

        private val solution = Solution()

        @ParameterizedTest(name = "isNumber({0})")
        @MethodSource("providerTrue")
        fun isNumber(input: String) {
            Assertions.assertTrue(solution.isNumber(input))
        }

        @ParameterizedTest(name = "isNotNumber({0})")
        @MethodSource("providerFalse")
        fun isNotNumber(input: String) {
            Assertions.assertFalse(solution.isNumber(input))
        }

        companion object {

            @JvmStatic
            fun providerTrue(): List<Arguments> = listOf(
                Arguments.of(".2e81"),
                Arguments.of("0.2e81"),
                Arguments.of("0"),
                Arguments.of("-0"),
                Arguments.of("+0.8"),
                Arguments.of("0."),
                Arguments.of(".3"),
                Arguments.of(" 0.1 "),
                Arguments.of("2e10"),
                Arguments.of("0e0"),
                Arguments.of("0e00"),
                Arguments.of("3E-3"),
                Arguments.of("+3E-3"),
                Arguments.of("46.e3"),
                Arguments.of("44e016912630333")
            )

            @JvmStatic
            fun providerFalse(): List<Arguments> = listOf(
                Arguments.of("-"),
                Arguments.of("+"),
                Arguments.of("."),
                Arguments.of(".."),
                Arguments.of(""),
                Arguments.of("abc"),
                Arguments.of("2e"),
                Arguments.of("0e"),
                Arguments.of("e0"),
                Arguments.of("0.3e"),
                Arguments.of(".e3"),
                Arguments.of("1.e3."),
                Arguments.of(" -.")
            )
        }
    }
}
