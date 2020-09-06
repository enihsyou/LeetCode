package leetcode.q2.kotlin;

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int = 0) {
 *     var next: ListNode? = null
 * }
 */
class Solution {

    class ListNode(var `val`: Int = 0) {

        var next: ListNode? = null
    }

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var carry = false
        val sum = ListNode(0)
        var p1 = l1
        var p2 = l2
        var ps = sum
        while (p1 != null || p2 != null) {
            /*计算两个个位数之和*/
            val s = (p1?.`val` ?: 0) + (p2?.`val` ?: 0)
            /*赋值到节点里*/
            val new_value = ListNode((carry.int + s) % 10)
            ps.next = new_value
            ps = ps.next!!
            /*计算进位*/
            carry = (carry.int + s) / 10 == 1
            /*移动指针*/
            p1?.let { p1 = it.next }
            p2?.let { p2 = it.next }
        }
        if (carry) ps.next = ListNode(1)
        return sum.next
    }

    val Boolean.int
        get() = if (this) 1 else 0

}

class SolutionTest {

    private val solution = Solution()

    /*缺陷 不能把输入当做一定长度的整数，应该是个很长的序列*/
    private fun Int.toIntArray() = toString().toCharArray().map { it - '0' }.toIntArray()

    private fun IntArray.toListNode() = fold(null) { acc: Solution.ListNode?, i: Int ->
        Solution.ListNode(i).also { it.next = acc }
    }

    private fun Solution.ListNode.collect(): Int {
        val array = StringBuilder()
        var p: Solution.ListNode? = this
        while (p != null) {
            array.append(p.`val`)
            p = p.next
        }
        return array.toString().reversed().toInt()
    }

    @ParameterizedTest(name = "addTwoNumbers({0}, {1}) = {2}")
    @MethodSource("provider")
    fun addTwoNumbers(node1: Int, node2: Int, output: Int) {
        assertThat(solution.addTwoNumbers(
            node1.toIntArray().toListNode(),
            node2.toIntArray().toListNode())?.collect())
            .isEqualTo(output)
    }

    companion object {

        @JvmStatic
        fun provider(): List<Arguments> {
            return listOf(
                Arguments.of(342, 465, 807),
                Arguments.of(1342, 465, 1807),
                Arguments.of(89751589, 1456471460, 1546223049)
            )
        }
    }
}
