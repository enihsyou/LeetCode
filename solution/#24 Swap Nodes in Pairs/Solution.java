package leetcode.q24.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import leetcode.base.struct.ListNode;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/swap-nodes-in-pairs/">
 * 24. Swap Nodes in Pairs
 * </a>
 */
class Solution {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;

        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(linklist(1, 2, 3, 4),
                             linklist(2, 1, 4, 3))
            );
        }
    }
}
