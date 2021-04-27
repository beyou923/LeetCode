## 题目描述

给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。candidates 中的每个数字在每个组合中只能使用一次。

**说明：**

* 所有数字（包括目标数）都是正整数。
* 解集不能包含重复的组合。

**示例 1:**

```shell
输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```
**示例 2:**
```shell
输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]
```
链接：[https://leetcode-cn.com/problems/combination-sum-ii](https://leetcode-cn.com/problems/combination-sum-ii?fileGuid=pVCTwQCCt6GKDgyQ)
## 思路

同上一题一样用回溯加剪枝解决，与上一题的区别在于：

1. 原数组中有重复元素
2. 每个元素只能选取一次

如何去重？

都知道组合问题可以抽象为树形结构，那么“使用过”在这个树形结构上是有两个维度的，一个维度是同一树枝上使用过，一个维度是同一树层上使用过。那么问题来了，我们是要同一树层上使用过，还是统一树枝上使用过呢？回看一下题目，元素在同一个组合内是可以重复的，怎么重复都没事，但两个组合不能相同。

为了理解去重我们来举一个例子，candidates = [1, 1, 2], target = 3，（方便起见candidates已经排序了）选择过程树形结构如图所示：

![图片](https://uploader.shimo.im/f/jgD2MI3P1haWIKR5.png!thumbnail?fileGuid=pVCTwQCCt6GKDgyQ)

根据上图可以知道：**要去重的是“同一树层上的使用过”**

与上一题的套路相同，此题还需要加一个bool型数组used，用来记录同一树枝上的元素是否使用过。这个集合去重的重任就是used来完成的。

## 代码

```java
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
```
