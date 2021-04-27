import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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
    // 递归实现
    List<Integer> result = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) return result;
        midTraversal(root);
        return result;
    }

    public void midTraversal(TreeNode root){
        if(root.left != null) midTraversal(root.left);
        result.add(root.val);
        if(root.right != null) midTraversal(root.right);
    }

    // 模拟递归
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> node = new LinkedList<>();
        if(root == null) return result;
        while(root != null || !node.isEmpty()){
            while(root != null){
                node.push(root);
                root = root.left;
            }
            root = node.pop();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }
}

