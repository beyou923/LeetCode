## 题目描述

给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。candidates 中的数字可以无限制重复被选取。

**说明：**

1. 所有数字（包括 target）都是正整数。
2. 解集不能包含重复的组合。

**示例 1：**

```shell
输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]
```
**示例 2：**
```shell
输入：candidates = [2,3,5], target = 8,
所求解集为：
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```
**提示：**
* 1 <= candidates.length <= 30
* 1 <= candidates[i] <= 200
* candidate 中的每个元素都是独一无二的。
* 1 <= target <= 500

链接：[https://leetcode-cn.com/problems/combination-sum](https://leetcode-cn.com/problems/combination-sum?fileGuid=K8X39J8chHQQcJGJ)

## 思路

跟组合相关的问题，可以优先考虑用回溯算法（DFS）来解决。

这里依然是定义两个全局变量，二维数组result存放结果集，数组path存放符合条件的结果。（这两个变量可以作为函数参数传入）。首先是题目中给出的参数，集合candidates, 和目标值target。此外还定义了int型的sum变量来统计单一结果path里的总和，最后如果target==0就说明找到符合的结果了。

![图片](https://uploader.shimo.im/f/gY9RoAezm9bPyR7H.png!thumbnail?fileGuid=K8X39J8chHQQcJGJ)

可以先对原数组排序后剪枝，可以先对原数组排序后剪枝，如果 sum + candidates[i] > target 就终止遍历。

## 代码

### 不带剪枝操作

```java
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
```
### 带剪枝操作

```java
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
```
