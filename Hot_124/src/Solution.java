class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(){}
    public TreeNode(int val){
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        dfs(root);
        return max;
    }
    // 返回经过root的单边分支最大和， 即Math.max(root, root + left, root + right)
    public int dfs(TreeNode root){
        if(root == null) return 0;
        // 计算左边分支最大值，左边分支如果为负数还不如不选择
        int left = Math.max(0,dfs(root.left));
        // 计算右边分支最大值，右边分支如果为负数还不如不选择
        int right = Math.max(0,dfs(root.right));
        // left->root->right 作为路径与已经计算过历史最大值做比较
        max = Math.max(max, root.val + left + right);
        // 返回经过root的单边最大分支给当前root的父节点计算使用
        return root.val + Math.max(left, right);
    }
}

