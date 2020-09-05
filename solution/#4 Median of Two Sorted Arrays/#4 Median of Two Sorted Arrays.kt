import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
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

class Solution4Test : StringSpec() {
    init {
        Solution4().run {
            "[1, 3], [2]" { findMedianSortedArrays(intArrayOf(1, 3), intArrayOf(2)) shouldBe 2.0 }
            "[1, 2], [3, 4]" { findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(3, 4)) shouldBe 2.5 }
            "[], []" { findMedianSortedArrays(intArrayOf(), intArrayOf()) shouldBe 0.0 }
            "[1,3,4,2,5,8,3,6], []" {
                findMedianSortedArrays(
                    intArrayOf(1, 3, 4, 2, 5, 8, 3, 6), intArrayOf()
                ) shouldBe 3.5
            }
            // FIXME: 2020/2/1 fix the error
//            add(intArrayOf(1, 2, 3), intArrayOf(2)) { a, b -> findMedianSortedArrays(a, b) shouldBe 2.0 }
        }
    }
}
