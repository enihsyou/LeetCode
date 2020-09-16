package leetcode.q535.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode-cn.com/problems/encode-and-decode-tinyurl/">
 * 535. Encode and Decode TinyURL
 * </a>
 */
public class Codec {

    private int count;
    private final Map<String, String> mapping = new HashMap<>();

    /** Encodes a URL to a shortened URL. */
    public String encode(String longUrl) {
        String hash = String.valueOf(count++);
        mapping.put(hash, longUrl);
        return "http://tinyurl.com/" + hash;
    }

    /** Decodes a shortened URL to its original URL. */
    public String decode(String shortUrl) {
        String hash = shortUrl.substring(shortUrl.lastIndexOf('/') + 1);
        return mapping.get(hash);
    }

    static class SolutionTest {

        @Test
        void case1() {
            Codec  codec  = new Codec();
            String input  = "https://leetcode.com/problems/design-tinyurl";
            assertThat(codec.decode(codec.encode(input))).isEqualTo(input);
        }
    }
}
