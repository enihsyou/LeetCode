import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.forAll
import io.kotlintest.tables.headers
import io.kotlintest.tables.row
import io.kotlintest.tables.table

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
