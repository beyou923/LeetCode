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
    // 这里一定要用long型，int可能会溢出
    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if(root == null ) return true;
        boolean left = isValidBST(root.left);
        if(root.val <= pre)
            return false;
        pre = root.val;
        boolean right = isValidBST(root.right);

        return left && right ;
    }
}

