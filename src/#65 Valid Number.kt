import io.kotlintest.matchers.shouldBe

operator fun Regex.contains(text: CharSequence): Boolean = this.matches(text)
class Solution65 {
    fun isNumber(s: String): Boolean {
        val trim = s.trim()
        val split = trim.split("[eE]".toRegex())
        val real_matcher = "[-+]?\\d+\\.\\d*|[-+]?\\d*\\.\\d+".toRegex()
        val integer_matcher = "[-+]?\\d+".toRegex()
        when {
            split.size == 2 -> {
                when (split[0]) {
                    in integer_matcher -> {
                    }

                    in real_matcher    -> {
                    }

                    else               -> return false
                }
                when (split[1]) {
                    in integer_matcher -> {
                    }

                    else               -> return false
                }
            }

            split.size == 1 -> {
                when (split[0]) {
                    in integer_matcher -> {
                    }

                    in real_matcher    -> {
                    }

                    else               -> return false
                }
            }

            else            -> return false
        }
        return true
    }
}

class Solution65Test : MySpec() {
    init {
        Solution65().run {
            "-"{ isNumber(it) shouldBe false }
            "+"{ isNumber(it) shouldBe false }
            "."{ isNumber(it) shouldBe false }
            ".."{ isNumber(it) shouldBe false }
            ""{ isNumber(it) shouldBe false }
            "abc"{ isNumber(it) shouldBe false }
            "2e"{ isNumber(it) shouldBe false }
            "0e"{ isNumber(it) shouldBe false }
            "e0"{ isNumber(it) shouldBe false }
            "0.3e"{ isNumber(it) shouldBe false }
            ".e3"{ isNumber(it) shouldBe false }
            "1.e3."{ isNumber(it) shouldBe false }
            " -."{ isNumber(it) shouldBe false }
            " 3-."{ isNumber(it) shouldBe false }
            ".2e81"{ isNumber(it) shouldBe true }
            "0.2e81"{ isNumber(it) shouldBe true }
            "0"{ isNumber(it) shouldBe true }
            "-0"{ isNumber(it) shouldBe true }
            "+0.8"{ isNumber(it) shouldBe true }
            "0."{ isNumber(it) shouldBe true }
            ".3"{ isNumber(it) shouldBe true }
            " 0.1 "{ isNumber(it) shouldBe true }
            "2e10"{ isNumber(it) shouldBe true }
            "0e0"{ isNumber(it) shouldBe true }
            "0e00"{ isNumber(it) shouldBe true }
            "3E-3"{ isNumber(it) shouldBe true }
            "+3E-3"{ isNumber(it) shouldBe true }
            "46.e3"{ isNumber(it) shouldBe true }
            "44e016912630333"{ isNumber(it) shouldBe true }
        }
    }
}
