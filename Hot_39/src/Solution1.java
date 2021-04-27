import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution1 {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> path = new ArrayList<Integer>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); // 对原数组排序，为剪枝做准备
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
        // DFS + 剪枝
        // 如果 sum + candidates[i] > target 就终止遍历
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++){
            sum += candidates[i];
            path.add(candidates[i]);
            dfs(candidates,target,sum,i); // 节点可以重复选取
            path.remove(path.size() - 1);
            sum -= candidates[i];
        }
    }
}
