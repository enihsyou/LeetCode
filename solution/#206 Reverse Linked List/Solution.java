package leetcode.q206.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import leetcode.base.struct.ListNode;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/reverse-linked-list/">
 * 206. Reverse Linked List
 * </a>
 */
class Solution {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode root = reverseList(head.next); // 以下一个元素为头 递归
        head.next.next = head; // 原先的下一个元素指向自己
        head.next      = null; // 自己的下一个元素指向NULL
        return root; // 返回最后的根节点，也就是最上面if的head
    }

    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev      = curr;
            curr      = temp;
        }
        return prev;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(linklist(1),
                             linklist(1)),
                Arguments.of(linklist(1, 2),
                             linklist(2, 1)),
                Arguments.of(linklist(1, 2, 3),
                             linklist(3, 2, 1)),
                Arguments.of(linklist(1, 2, 3, 4, 5),
                             linklist(5, 4, 3, 2, 1))
            );
        }
    }
}
