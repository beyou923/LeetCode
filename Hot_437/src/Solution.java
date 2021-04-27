import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Definition for a binary tree node.
 *  */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution1 {
    List<TreeNode> path = new ArrayList<>();
    int count;
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) return 0;
        dfs(root, targetSum, 0, path);
        return count;
    }

    public void dfs(TreeNode root, int targetSum, int currSum, List<TreeNode> path){
        if(root == null) return;

        currSum += root.val;
        path.add(root);
        if(currSum == targetSum) count++;

        int tmp = currSum;
        for(int i = 0; i < path.size() - 1; i++){
            tmp -= path.get(i).val;
            if(targetSum == tmp) count++;
        }

        dfs(root.left, targetSum, currSum, path);
        dfs(root.right, targetSum, currSum, path);
        path.remove(path.size() -1);
    }
}

class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) return 0;
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0,1);
        return dfs(root, targetSum, 0,prefixSum);
    }

    public int dfs(TreeNode root, int targetSum, int currSum, Map<Integer, Integer> prefixSum){
        if(root == null) return 0;
        currSum += root.val;
        int res = prefixSum.getOrDefault(currSum - targetSum,0);
        prefixSum.put(currSum,prefixSum.getOrDefault(currSum,0) + 1);
        res += dfs(root.left,targetSum, currSum, prefixSum) + dfs(root.right, targetSum,currSum,prefixSum);
        prefixSum.put(currSum, prefixSum.get(currSum) - 1);
        return res;
    }
}
