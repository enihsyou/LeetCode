class Solution65 {
    operator fun Regex.contains(text: CharSequence): Boolean = this.matches(text)

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
