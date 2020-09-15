package leetcode.q226.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import leetcode.base.struct.TreeNode;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/invert-binary-tree/">
 * 226. Invert Binary Tree
 * </a>
 */
class Solution {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            //noinspection ReturnOfNull
            return null;
        } else {
            TreeNode lefts = root.left;
            TreeNode right = root.right;
            root.right = lefts != null ? invertTree(lefts) : null;
            root.left  = right != null ? invertTree(right) : null;
            return root;
        }
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(node(4,
                                  node(2, 1, 3),
                                  node(7, 6, 9)),
                             node(4,
                                  node(7, 9, 6),
                                  node(2, 3, 1))),
                Arguments.of(node(4, node(2, null, 3), null),
                             node(4, null, node(2, 3, null)))
            );
        }

    }
}
