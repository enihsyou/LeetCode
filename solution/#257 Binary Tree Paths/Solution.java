package leetcode.q257.java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import leetcode.base.struct.TreeNode;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/binary-tree-paths/">
 * 257. Binary Tree Paths
 * </a>
 */
class Solution {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> answer = new ArrayList<>();
        dfs(answer, root, new StringBuilder());
        return answer;
    }

    private static void dfs(List<String> answer, TreeNode root, StringBuilder builder) {
        if (root == null) {
            return;
        }
        builder.append(root.val);
        if (root.left == null && root.right == null) {
            answer.add(builder.toString());
            return;
        }
        // 有子节点 需要继续，先添加->
        builder.append("->");
        int length = builder.length();
        if (root.left != null) {
            dfs(answer, root.left, builder);
            builder.setLength(length); // trim
        }
        if (root.right != null) {
            dfs(answer, root.right, builder);
            builder.setLength(length); // trim
        }
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(node(1,
                                  node(2, null, 5),
                                  node(3)),
                             lists("1->2->5", "1->3")),
                Arguments.of(node(1),
                             lists("1")),
                Arguments.of(node(1, node(2, null, 5), null),
                             lists("1->2->5")),
                Arguments.of(node(1,
                                  node(2,
                                       node(3, 4, 5),
                                       node(3, 6, 7)),
                                  node(8, 1, 9)),
                             lists("1->2->3->4", "1->2->3->5",
                                   "1->2->3->6", "1->2->3->7",
                                   "1->8->1", "1->8->9"))
            );
        }
    }
}
