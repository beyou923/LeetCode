class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    public int rob(TreeNode root) {
        int[] dp = robTree(root);
        return Math.max(dp[0],dp[1]);
    }

    public int[] robTree(TreeNode root){
        if(root == null) return new int[]{0,0};

        // 下标0：不偷  下标1：偷
        int[] left = robTree(root.left);
        int[] right = robTree(root.right);

        // 偷当前节点,则子节点不能偷
        int val1 = root.val + left[0] + right[0];
        // 不偷当前节点，那么子节点可偷可不偷，就需要分别从左右孩子中选择一个收益最大的
        int val2 = Math.max(left[0],left[1]) + Math.max(right[0], right[1]);
        // 返回偷与不偷当前节点的收益
        return new int[]{val2,val1};
    }
}