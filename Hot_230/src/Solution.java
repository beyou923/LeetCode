/**
 * Definition for a binary tree node.
 *  */
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
    int result, count;
    public int kthSmallest(TreeNode root, int k) {
        if(root.left != null)
            kthSmallest(root.left, k);
        if(++count == k){
            result = root.val;
        }
        if(root.right != null)
            kthSmallest(root.right,k);

        return result;
    }
}

