import java.util.HashMap;
import java.util.Map;

/** 13. Roman to Integer */
class Q13 {

    static Map<Character, Integer> map = new HashMap<>();

    static {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    public int romanToInt(String s) {
        int count = 0;
        int last = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            int translate = map.get(c);
            if (last > translate) {
                count -= translate;
            } else {
                count += translate;
            }

            last = translate;
        }
        return count;
    }
}

