import io.kotlintest.properties.forAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class Solution371 {
    fun getSum(a: Int, b: Int): Int = a.plus(b)
}

class Solution371Test : StringSpec() {
    init {
        Solution371().run {
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
