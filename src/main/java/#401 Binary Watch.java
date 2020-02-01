import java.util.ArrayList;
import java.util.List;

/**
 * #401 Binary Watch
 */
class Q401 {

    public static final int MINUTE_MASK = 0b111111;

    public static final int HOUR_MASK = 0b1111000000;

    public static final int TEN_BITS = 0b10000000000;

    private List<String> result;

    public List<String> readBinaryWatch(int num) {
        result = new ArrayList<>(1024);

        for (int i = 0; i < TEN_BITS; i++) {
            if (Integer.bitCount(i) == num) {
                int hour = (i & HOUR_MASK) >> 6;
                if (hour > 11) continue;
                int min = i & MINUTE_MASK;
                if (min > 59) continue;
                result.add(hour + ":" + (min < 10 ? "0" + min : min));
            }
        }
        return result;
    }

    public List<String> readBinaryWatch1(int num) {
        result = new ArrayList<>(1024);

        helper(1, 0, num);

        return result;
    }

    /**
     * @param base  二进制计数
     * @param light 当前点亮的灯数
     * @param num   还需要点亮多少
     */
    private void helper(int base, int light, int num) {
        if (num == 0) {
            int hour = (light & HOUR_MASK) >> 6;
            if (hour > 11) return;
            int min = light & MINUTE_MASK;
            if (min > 59) return;
            result.add(hour + ":" + (min < 10 ? "0" + min : min));
            return;
        }
        if (base >= TEN_BITS) return;
        final int next = base << 1;
        helper(next, light + base, num - 1);
        helper(next, light, num);
    }

}
