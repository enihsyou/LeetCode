/**
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int = 0) {
 *     var next: ListNode? = null
 * }
 */
class Solution2 {

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

    /*缺陷 不能把输入当做一定长度的整数，应该是个很长的序列*/
    fun Int.toIntArray() = toString().toCharArray().map { it - '0' }.toIntArray()

    fun Long.toIntArray() = toString().toCharArray().map { it - '0' }.toIntArray()
    fun IntArray.toListNode() = fold(null) { acc: ListNode?, i: Int ->
        ListNode(i).also { it.next = acc }
    }

    fun ListNode.collect(): Long {
        val array = StringBuilder()
        var p: ListNode? = this
        while (p != null) {
            array.append(p.`val`)
            p = p.next
        }
        return array.toString().reversed().toLong()
    }
}
