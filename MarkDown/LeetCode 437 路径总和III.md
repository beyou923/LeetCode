## 题目

给定一个二叉树，它的每个结点都存放着一个整数值。找出路径和等于给定数值的路径总数。

路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。

**示例**：

>root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
>>10
>/  \
>5   -3
>/ \    \
>3   2   11
>/ \   \
>3  -2   1
>>返回 3。和等于 8 的路径有:
>>1.  5 -> 3
>2.  5 -> 2 -> 1
>3.  -3 -> 11

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/path-sum-iii](https://leetcode-cn.com/problems/path-sum-iii?fileGuid=QYp9r9JRkDrkXx6d)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：回溯法 + 前缀和

可以用递归来做，相当于先序遍历二叉树，对于每一个节点都有记录了一条从根节点到当前节点到路径，同时用一个变量 curSum 记录路径节点总和，然后看 curSum 和 sum 是否相等，相等的话结果 res 加1，不等的话继续查看子路径和有没有满足题意的，做法就是每次去掉一个节点，看路径和是否等于给定值，注意最后必须留一个节点，不能全去掉了，因为如果全去掉了，路径之和为0，而如果给定值刚好为0的话就会有问题

```java
class Solution {
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
```
时间复杂度：O(n^2)
空间复杂度：O(n)

还可以对上面的方法进行一些优化，来去掉一些不必要的计算，可以用 HashMap 来建立路径之和跟其个数之间的映射，即路径之和为 curSum 的个数为 m[curSum]，这里需要将 m[0] 初始化为1，相当于前缀和为0的路径有1条。在递归函数中，首先判空，若为空，直接返回0。然后就是 curSum 加上当前结点值。由于此时 curSum 可能已经大于了目标值 sum，所以用 curSum 减去 sum，并去 HashMap 中查找这个差值的映射值，若映射值大于0的话，说明存在结束点为当前结点且和为 sum 的路径，这就相当于累加和数组快速求某个区域和的原理。当 curSum 等于 sum 的时候，表明从根结点到当前结点正好是一条符合要求的路径，此时 res 应该大于0，这就是为啥要初始化 m[0] 为1的原因

```java
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
```
时间复杂度：O(n)
空间复杂度:O(n)

