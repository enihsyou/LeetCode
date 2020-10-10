package leetcode.q829.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import leetcode.base.java.DiffMode;
import leetcode.base.java.ExecutionOption;
import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/subdomain-visit-count/">
 * 811. Subdomain Visit Count
 * </a>
 */
class Solution {

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();

        for (String cpdomain : cpdomains) {
            String[] split = cpdomain.split(" ");
            int count = Integer.parseInt(split[0]);
            String[] subdomains = split[1].split("\\.");

            StringBuilder current = new StringBuilder(64);
            for (int i = subdomains.length - 1; i >= 0; --i) {
                if (i == subdomains.length - 1) { current.append(subdomains[i]); } else {
                    current.insert(0, '.').insert(0, subdomains[i]);
                }
                String key = current.toString();
                map.put(key, map.getOrDefault(key, 0) + count);
            }
        }
        List<String> result = new ArrayList<>();
        for (String str : map.keySet()) {
            StringBuilder builder = new StringBuilder(64);
            result.add(builder.append(map.get(str)).append(' ').append(str).toString());
        }
        return result;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected void tweakExecutionOption(ExecutionOption option) {
            option.diffMode(DiffMode.CONTAIN);
        }

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(strings("9001 discuss.leetcode.com"),
                             lists("9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com")),
                Arguments.of(strings("900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"),
                             lists("901 mail.com", "50 yahoo.com", "900 google.mail.com", "5 wiki.org", "5 org",
                                   "1 intel.mail.com", "951 com"))
            );
        }
    }
}
