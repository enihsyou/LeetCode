package leetcode.q94.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import leetcode.base.struct.TreeNode;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/binary-tree-inorder-traversal/">
 * 94. Binary Tree Inorder Traversal
 * </a>
 */
class Solution {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        dfs(answer, new ArrayDeque<>(), root);
        return answer;
    }

    private static void dfs(List<Integer> answer, Deque<Integer> stack, TreeNode root) {
        if (root == null) {
            return;
        }
        stack.push(root.val);
        dfs(answer, stack, root.left);
        answer.add(stack.pop());
        dfs(answer, stack, root.right);
    }

    public List<Integer> inorderTraversa2(TreeNode root) {
        List<Integer>   answer = new ArrayList<>();
        Deque<TreeNode> stack  = new ArrayDeque<>();

        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            answer.add(node.val);
            node = node.right;
        }
        return answer;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(node(1, null, node(2, node(3), null)),
                             lists(1, 3, 2)),
                Arguments.of(node(1, node(4, node(5), null), node(2, node(3), null)),
                             lists(5, 4, 1, 3, 2))
            );
        }
    }
}
