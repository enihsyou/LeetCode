package leetcode.q142.java;

import java.util.stream.Stream;

import leetcode.base.java.ExecutionOption;
import leetcode.base.java.JavaTest;
import leetcode.base.struct.ListNode;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/linked-list-cycle-ii/">
 * 142. Linked List Cycle II
 * </a>
 */
public class Solution {

    @SuppressWarnings("ReturnOfNull")
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head, fast = head;
        do {
            if (fast == null || fast.next == null) {
                return null;
            } else {
                slow = slow.next;
                fast = fast.next.next;
            }
        } while (fast != slow);
        ListNode flow = head;
        while (flow != slow) {
            flow = flow.next;
            slow = slow.next;
        }
        return flow;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected void tweakExecutionOption(ExecutionOption option) {
            option.allowNullOutput(true);
        }

        @Override
        protected Stream<Arguments> provider() {
            ListNode loop1 = linklistLoop(2, 0, -4);
            ListNode loop2 = linklistLoop(1, 2);
            ListNode loop3 = linklistLoop(1);

            ListNode list1 = linklist(3);
            list1.next = loop1;
            return Stream.of(
                Arguments.of(list1, loop1),
                Arguments.of(loop1, loop1),
                Arguments.of(loop2, loop2),
                Arguments.of(loop3, loop3),
                Arguments.of(linklist(1), null),
                Arguments.of(linklist(2, 0, -4), null)
            );
        }
    }
}
