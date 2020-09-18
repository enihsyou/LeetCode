package leetcode.q404.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import leetcode.base.struct.TreeNode;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/sum-of-left-leaves/">
 * 404. Sum of Left Leaves
 * </a>
 */
class Solution {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sumOfLeftLeaves(root.left, true) +
               sumOfLeftLeaves(root.right, false);
    }

    private static int sumOfLeftLeaves(TreeNode root, boolean isLeft) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            // 到达叶子节点
            if (isLeft) {
                return root.val;
            } else {
                return 0;
            }
        }
        // 返回左右子树之和
        return sumOfLeftLeaves(root.left, true) +
               sumOfLeftLeaves(root.right, false);
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(node(1,
                                  node(2, null, 5),
                                  node(3)), 0),
                Arguments.of(node(1), 0),
                Arguments.of(node(1, node(2, null, 5), null), 0),
                Arguments.of(node(1,
                                  node(2,
                                       node(3, 4, 5),
                                       node(3, 6, 7)),
                                  node(8, 1, 9)), 4 + 6 + 1),
                Arguments.of(node(3,
                                  node(9),
                                  node(20, 15, 7)), 9 + 15)
            );
        }
    }
}
