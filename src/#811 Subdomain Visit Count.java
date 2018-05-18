import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Q811_Subdomain_Visit_Count {

    public static void main(String[] args) {
        Q811_Subdomain_Visit_Count solution = new Q811_Subdomain_Visit_Count();
        System.out.println(solution.subdomainVisits(
            new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"}));
    }

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();

        for (final String cpdomain : cpdomains) {
            final String[] split = cpdomain.split(" ");
            final int count = Integer.parseInt(split[0]);
            final String[] subdomains = split[1].split("\\.");

            StringBuilder current = new StringBuilder(64);
            for (int i = subdomains.length - 1; i >= 0; --i) {
                if (i == subdomains.length - 1)
                    current.append(subdomains[i]);
                else
                    current.insert(0, '.').insert(0, subdomains[i]);
                final String key = current.toString();
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
}
