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

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true; // 根节点为空直接返回true
        return symmetric(root.left, root.right); // 递归判断左右子树
    }
    public boolean symmetric(TreeNode t1, TreeNode t2){
        if(t1 == null || t2 == null) return t1 == t2;// 2棵子树根节点为空返回true；有一棵不为空返回false
        return t1.val == t2.val && symmetric(t1.left,t2.right) && symmetric(t1.right,t2.left);
    }
}