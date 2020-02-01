class Q20 {

    private static char[] open = new char[]{'(', '{', '['};

    private static char[] close = new char[]{')', '}', ']'};

    public boolean isValid(String s) {
        final int stack_size = (s.length() + 1) / 2;
        int[] left = new int[stack_size];
        int length = 0;

        for (int i = 0; i < s.length(); ++i) {
            final char c = s.charAt(i);

            final int open = containOpen(c);
            if (open >= 0) {
                /*Fail fast*/
                if (length >= stack_size) return false;

                left[length] = open;
                ++length;
                continue;
            }

            final int close = containClose(c);
            if (close >= 0) {
                --length;
                if (length < 0 || left[length] != close) return false;
                continue;
            }

            throw new IllegalArgumentException(String.valueOf(c));
        }

        return length == 0;
    }

    private static int containOpen(char c) {
        final int bound = open.length;
        for (int i = 0; i < bound; ++i) {
            if (c == open[i]) return i;
        }
        return -1;
    }

    private static int containClose(char c) {
        final int bound = close.length;
        for (int i = 0; i < bound; ++i) {
            if (c == close[i]) return i;
        }
        return -1;
    }

}

