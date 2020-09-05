import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.forAll
import io.kotlintest.tables.headers
import io.kotlintest.tables.row
import io.kotlintest.tables.table

class Solution5 {
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

class Solution5Test : StringSpec() {
    init {
        Solution5().run {
            "should" {
                val table = table(
                    headers("input", "output"),
                    row("babad", "bab"),
                    row("cbbd", "bb"),
                    row("", ""),
                    row("a", "a")
                )
                forAll(table) { input, output ->
                    longestPalindrome(input).also { println(it) } shouldBe output
                }
            }
        }
    }
}
