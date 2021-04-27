import java.util.ArrayList;
import java.util.List;

class Solution {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0);
        return result;
    }

    public void dfs(int[] nums, int startIndex){
        result.add(new ArrayList<>(path));
        if(startIndex >= nums.length) // 可以不要这个判断
            return;
        for(int i = startIndex; i < nums.length; i++){
            path.add(nums[i]);
            dfs(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
