package leetcode.q100187.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import leetcode.base.java.AssertMode;
import leetcode.base.java.JavaTest;
import leetcode.base.struct.ListNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/delete-middle-node-lcci/">
 * 面试题 02.03. Delete Middle Node LCCI
 * </a>
 */
class Solution {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Test
        void case1() {
            ListNode root     = linklist(1, 2, 3, 4, 5, 6);
            Solution solution = new Solution();
            solution.deleteNode(root.next.next);
            assertThat(root).isEqualTo(linklist(1, 2, 4, 5, 6));
        }

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of();
        }
    }
}
