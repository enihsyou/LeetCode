package leetcode.q23.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import leetcode.base.struct.ListNode;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/merge-k-sorted-lists/">
 * 23. Merge k Sorted Lists
 * </a>
 */
class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode root = null;
        if (lists != null) {
            for (ListNode list : lists) {
                root = mergeTwoLists(root, list);
            }
        }
        return root;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        return mergeRangeLists(lists, 0, lists.length - 1);
    }

    private static ListNode mergeRangeLists(ListNode[] lists, int lower, int right) {
        if (lower == right) {
            return lists[lower];
        }
        if (lower > right) {
            return null;
        }
        int middle = (lower + right) / 2;
        return mergeTwoLists(mergeRangeLists(lists, lower, middle),
                             mergeRangeLists(lists, middle + 1, right));
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
                Arguments.of(new ListNode[]{ linklist(1, 4, 5),
                                             linklist(1, 3, 4),
                                             linklist(2, 6), },
                             linklist(1, 1, 2, 3, 4, 4, 5, 6))
            );
        }
    }
}
