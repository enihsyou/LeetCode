import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

class Solution3Test : WordSpec() {
    init {
        Solution3().run {

            "abcabcbb".should{ lengthOfLongestSubstring("abcabcbb") shouldBe 3 }
            "bbbbb".should{ lengthOfLongestSubstring("bbbbb") shouldBe 1 }
            "pwwkew".should{ lengthOfLongestSubstring("pwwkew") shouldBe 3 }
            "dfaergsbcsbfrehsddf".should{ lengthOfLongestSubstring("dfaergsbcsbfrehsddf") shouldBe 9 }
            "tmmzuxt".should{ lengthOfLongestSubstring("tmmzuxt") shouldBe 5 }
        }
    }
}
