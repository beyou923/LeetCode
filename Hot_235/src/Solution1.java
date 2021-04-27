public class Solution1 {
    // 递归实现
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((root.val - p.val) * (root.val - q.val) <= 0)
            return root;
        return lowestCommonAncestor(root.val > p.val ? root.left : root.right, p, q);
    }
}
