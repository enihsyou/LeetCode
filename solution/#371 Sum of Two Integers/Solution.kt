package leetcode.q371.kotlin

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Solution {

    fun getSum(a: Int, b: Int): Int = a.plus(b)

    class SolutionTest {

        private val solution = Solution()

        @ParameterizedTest(name = "getSum({0}, {1}) = {2}")
        @MethodSource("provider")
        fun getSum(a: Int, b: Int, c: Int) {
            Assertions.assertEquals(solution.getSum(a, b), c)
        }

        companion object {

            @JvmStatic
            fun provider(): List<Arguments> {
                return listOf(
                    Arguments.of(1, 1, 2),
                    Arguments.of(1, 2, 3),
                    Arguments.of(2, 1, 3)
                )
            }
        }
    }
}
