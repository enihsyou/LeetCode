package leetcode.q141.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import leetcode.base.struct.ListNode;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/linked-list-cycle/">
 * 141. Linked List Cycle
 * </a>
 */
public class Solution {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head, fast = head.next;

        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return false;
            } else {
                slow = slow.next;
                fast = fast.next.next;
            }
        }
        return true;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {

            return Stream.of(
                Arguments.of(linklist(3).next = linklistLoop(2, 0, -4), true),
                Arguments.of(linklistLoop(1, 2), true),
                Arguments.of(linklistLoop(1), true),
                Arguments.of(linklist(1), false),
                Arguments.of(linklist(2, 0, -4), false)
            );
        }
    }
}
