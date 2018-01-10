import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec

class Solution {
    fun getSum(a: Int, b: Int): Int = a.plus(b)
}

class SolutionTest : StringSpec() {
    init {
        Solution().run {
            "1 + 1 = 2"{
                getSum(1, 1) shouldBe 2
            }
            "1 + 2 = 3"{
                getSum(1, 2) shouldBe 3
                getSum(2, 1) shouldBe 3
            }
            "Adding numbers"{
                forAll { a: Int, b: Int ->
                    getSum(a, b) == a + b
                }
            }
        }
    }
}

