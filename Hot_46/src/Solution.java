import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int[] visted = new int[nums.length];
        dfs(result,path,0,nums,visted);
        return result;
    }

    public void dfs(List<List<Integer>> result, List<Integer> path, int n,int[] nums,int[] visted){
        if(path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(visted[i] == 1) continue;
            visted[i] = 1;
            path.add(nums[i]);
            dfs(result,path,n + 1, nums,visted);
            path.remove(path.size() -1);
            visted[i] = 0;
        }
    }
}
