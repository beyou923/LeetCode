import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target){
        boolean[] used = new boolean[candidates.length];
        Arrays.sort(candidates);
        dfs(candidates,target,0,0,used);
        return result;
    }
    // DFS
    private void dfs(int[] candidates,int target,int sum, int startIndex, boolean[] used){
        if (sum > target)
            return;
        if (sum == target){
            result.add(new ArrayList<>(path));
            return;
        }
        // DFS + 剪枝
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++){
            // used[i - 1] == true，说明同一树支candidates[i - 1]使用过
            // used[i - 1] == false，说明同一树层candidates[i - 1]使用过
            // 要对同一树层使用过的元素进行跳过
            if (i > 0 && candidates[i] == candidates[i -1] && used[i - 1] == false)
                continue;
            sum += candidates[i];
            path.add(candidates[i]);
            used[i] = true;
            dfs(candidates,target,sum,i + 1,used); // 和39题组合总和的区别1，这里是i+1，每个数字在每个组合中只能使用一次
            used[i] = false;
            sum -= candidates[i];
            path.remove(path.size() - 1);
        }
    }
    public static void main(String[] args){
        Solution solution = new Solution();
        int[] candidates = new int[]{10,1,2,7,6,1,5};
        List<List<Integer>> res = solution.combinationSum2(candidates,8);
        for (List<Integer> path:res) {
            System.out.println(path);
        }
    }
}
