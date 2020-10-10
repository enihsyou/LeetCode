package leetcode.base.struct;

import java.util.Objects;

public class ListNode {

    public int      val;
    public ListNode next;

    public ListNode(int x) { val = x; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListNode listNode = (ListNode) o;
        return val == listNode.val &&
               Objects.equals(next, listNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

    @Override
    public String toString() {
        return toString(detectCycle(this));
    }

    private String toString(ListNode crossPoint) {
        StringBuilder sb = new StringBuilder();

        boolean  meet = false;
        ListNode node = this;
        do {
            if (crossPoint == node && meet) {
                sb.append("]");
                break;
            } else {
                if (sb.length() > 0) {
                    sb.append("→");
                }
                if (crossPoint == node) {
                    sb.append("[");
                    meet = true;
                }
                sb.append(node.val);
            }
        } while ((node = node.next) != null);
        return sb.toString();
    }

    @SuppressWarnings("ReturnOfNull")
    private static ListNode detectCycle(ListNode head) {
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
}
