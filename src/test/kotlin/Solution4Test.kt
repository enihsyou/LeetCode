import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class Solution4Test : StringSpec() {
    init {
        Solution4().run {
            "[1, 3], [2]" { findMedianSortedArrays(intArrayOf(1, 3), intArrayOf(2)) shouldBe 2.0 }
            "[1, 2], [3, 4]" { findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(3, 4)) shouldBe 2.5 }
            "[], []" { findMedianSortedArrays(intArrayOf(), intArrayOf()) shouldBe 0.0 }
            "[1,3,4,2,5,8,3,6], []" {
                findMedianSortedArrays(
                    intArrayOf(1, 3, 4, 2, 5, 8, 3, 6), intArrayOf()) shouldBe 3.5
            }
//            add(intArrayOf(1, 2, 3), intArrayOf(2)) { a, b -> findMedianSortedArrays(a, b) shouldBe 2.0 }
        }
    }
}
