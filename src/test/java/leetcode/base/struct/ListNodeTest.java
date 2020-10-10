package leetcode.base.struct;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-10-10
 */
class ListNodeTest {

    @Test
    void nonLoop() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = node1.next = new ListNode(2);
        ListNode node3 = node2.next = new ListNode(3);
        ListNode node4 = node3.next = new ListNode(4);
        ListNode node5 = node4.next = new ListNode(5);
        assertThat(node1.toString()).isEqualTo("1→2→3→4→5");
        assertThat(node5.toString()).isEqualTo("5");
    }

    @Test
    void loop() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = node1.next = new ListNode(2);
        ListNode node3 = node2.next = new ListNode(3);
        ListNode node4 = node3.next = new ListNode(4);
        ListNode node5 = node4.next = new ListNode(5);
        node5.next = node2;
        assertThat(node1.toString()).isEqualTo("1→[2→3→4→5]");
        assertThat(node2.toString()).isEqualTo("[2→3→4→5]");
        assertThat(node3.toString()).isEqualTo("[3→4→5→2]");
    }

    @Test
    void smallLoop() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = node1.next = new ListNode(2);
        node2.next = node1;
        assertThat(node1.toString()).isEqualTo("[1→2]");
        assertThat(node2.toString()).isEqualTo("[2→1]");
    }

    @Test
    void equalAndHashcode() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        assertThat(node1).isNotEqualTo(node2);
        assertThat(node1).isEqualTo(node1);
        assertThat(node1).isEqualTo(new ListNode(1));
        assertThat(node1.hashCode()).isNotZero();
    }
}
