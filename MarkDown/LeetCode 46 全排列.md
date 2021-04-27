## 题目

给定一个 没有重复 数字的序列，返回其所有可能的全排列。

**示例:**

>输入: [1,2,3]
>输出:
>[
>[1,2,3],
>[1,3,2],
>[2,1,3],
>[2,3,1],
>[3,1,2],
>[3,2,1]
>]

链接：[https://leetcode-cn.com/problems/permutations](https://leetcode-cn.com/problems/permutations?fileGuid=CDVxhXTKkWCpPQDX)

## 思路

用DFS求解

```java
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
```
