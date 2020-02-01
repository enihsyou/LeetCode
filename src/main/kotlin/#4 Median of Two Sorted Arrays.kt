import java.util.LinkedList

class Solution4 {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val m = nums1.size
        val n = nums2.size
        val queue1 = LinkedList<Int>(nums1.toList())
        val queue2 = LinkedList<Int>(nums2.toList())
        fun nextSmall(): Int {
            if (queue1.isNotEmpty() && queue2.isNotEmpty()) {
                val a = queue1.element()
                val b = queue2.element()
                return if (a < b) queue1.remove() else queue2.remove()
            } else if (queue1.isNotEmpty()) {
                return queue1.remove()
            } else if (queue2.isNotEmpty()) {
                return queue2.remove()
            }
            return 0
        }

        var c = 0
        while (c < (m + n + 1) / 2 - 1) {
            nextSmall()
            c++
        }
        return if ((m + n) % 2 == 0) {
            (nextSmall() + nextSmall()) / 2.0
        } else {
            nextSmall().toDouble()
        }
    }
}
