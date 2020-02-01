import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.forAll
import io.kotlintest.tables.headers
import io.kotlintest.tables.row
import io.kotlintest.tables.table

class Solution15Test : StringSpec() {
    init {
        Solution15().run {
            "should" {
                val table = table(
                    headers("input", "output"),
                    row(intArrayOf(-1, 0, 1, 2, -1, -4), listOf(listOf(-1, 0, 1), listOf(-1, -1, 2)))
                )
                forAll(table) { input, output ->
                    threeSum(input).also { println(it) } shouldBe output
                }
            }
        }
    }
}
