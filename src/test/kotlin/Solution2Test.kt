import io.kotlintest.properties.forAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class Solution2Test : StringSpec() {
    init {
        Solution2().run {
            "342 + 465 = 807" {
                val node = addTwoNumbers(342.toIntArray().toListNode(), 465.toIntArray().toListNode())!!
                node.`val` shouldBe 7
                node.next!!.`val` shouldBe 0
                node.next!!.next!!.`val` shouldBe 8
                node.next!!.next!!.next shouldBe null
            }
            "1342 + 465 = 1807" {
                val node = addTwoNumbers(1342.toIntArray().toListNode(), 465.toIntArray().toListNode())!!
                node.collect() shouldBe 1807L
            }
            "89751589 + 1456471460 = 1546223049" {
                val node = addTwoNumbers(89751589.toIntArray().toListNode(), 1456471460.toIntArray().toListNode())!!
                node.collect() shouldBe 1546223049L
            }
            "Adding numbers" {
                forAll { a: Int, b: Int ->
                    if (a < 0 || b < 0)
                        true
                    else {
                        val aArray = a.toIntArray()
                        val bArray = b.toIntArray()
                        val aNode = aArray.toListNode()
                        val bNode = bArray.toListNode()
                        val result = addTwoNumbers(aNode, bNode)?.collect()
                        println("$a + $b = ${a.toLong() + b} $result")
                        result == a.toLong() + b
                    }
                }
            }
        }
    }
}
