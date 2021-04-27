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
    int deep = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return deep;
    }
    public int depth(TreeNode root) {
        if (root == null) {
            return 0; // 访问到空节点了，返回0
        }
        int left = depth(root.left); // 左儿子为根的子树的深度
        int right = depth(root.right); // 右儿子为根的子树的深度
        if(deep < left + right)
            deep = left + right;
        return Math.max(left, right) + 1; // 返回该节点为根的子树的深度
    }
    public static void main(String[] args){
        Solution s = new Solution();
        TreeNode left = new TreeNode(2,null,null);
        TreeNode right = new TreeNode(3,null,null);
        TreeNode root = new TreeNode(1, left,right);
        int result = s.diameterOfBinaryTree(root);
        System.out.println(result);
    }
}
