import java.util.ArrayList;
import java.util.List;

class Solution {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> path = new ArrayList<Integer>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, 0, 0);
        return result;
    }
    // DFS 回溯算法
    public void dfs(int[] candidates, int target, int sum , int startIndex){
        if (sum > target)
            return;
        if (sum == target){
            result.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++){
            sum += candidates[i];
            path.add(candidates[i]);
            dfs(candidates,target,sum,i); // 节点可以重复选取
            path.remove(path.size() - 1);
            sum -= candidates[i];
        }
    }
}
