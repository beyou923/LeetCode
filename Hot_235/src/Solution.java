/**
 * Definition for a binary tree node.
 *  */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


public class Solution {
    // 循环实现
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //如果根节点和p,q的差相乘是正数，说明这两个差值要么都是正数要么都是负数，也就是说
        //他们肯定都位于根节点的同一侧，就继续往下找
        while((root.val - p.val) * (root.val - q.val) > 0){
            root = root.val > p.val ? root.left : root.right;
        }
        //如果相乘的结果是负数，说明p和q位于根节点的两侧；如果等于0，说明至少有一个就是根节点
        return root;
    }
}

