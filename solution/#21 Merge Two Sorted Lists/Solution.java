package leetcode.q21.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import leetcode.base.struct.ListNode;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/merge-two-sorted-lists/">
 * 21. Merge Two Sorted Lists
 * </a>
 */
class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(-1);

        ListNode prev = root;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1        = l1.next;
            } else {
                prev.next = l2;
                l2        = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，
        // 我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return root.next;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(linklist(1, 2, 4), linklist(1, 3, 4),
                             linklist(1, 1, 2, 3, 4, 4)),
                Arguments.of(linklist(2, 2, 4), linklist(1, 3, 4),
                             linklist(1, 2, 2, 3, 4, 4))
            );
        }
    }
}
