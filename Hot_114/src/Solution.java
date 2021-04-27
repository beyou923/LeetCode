/**
 * Definition for a binary tree node.
 */
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
    public void flatten(TreeNode root) {
        if(root == null) return;
        flatten(root.right);
        flatten(root.left);
        TreeNode curr = root.left;

        if(curr == null) return;
        // 找到左边链表的尾
        while(curr.right != null){
            curr = curr.right;
        }
        // root.right是右边链表的头
        curr.right = root.right;
        // root.left 是左边链表的头
        root.right = root.left;
        root.left = null;
    }
}

