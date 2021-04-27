## 题目

在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

**示例 1**:

>输入: [3,2,3,null,3,null,1]
>>3
>/ \
>2   3
>\   \
>3   1
>>输出: 7
>解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.

**示例 2**:

>输入: [3,4,5,1,3,null,1]
>>3
>/ \
>4   5
>/ \   \
>1   3   1
>>输出: 9
>解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/house-robber-iii](https://leetcode-cn.com/problems/house-robber-iii?fileGuid=HpwcGQTcHHXvDJcc)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 暴力法

```java
class Solution {
    public int rob(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return root.val;
        // 专门偷父节点
        int valRoot = root.val;
        if(root.left != null)  valRoot += rob(root.left.left) + rob(root.left.right);
        if(root.right != null) valRoot += rob(root.right.left) + rob(root.right.right);
        // 不偷root节点
        int valChild = rob(root.left) + rob(root.right); // 考虑root的左右孩子节点
        return Math.max(valRoot,valChild);
    }
}
```
超时！！！
### 方法二：动态规划

* 确定递归函数的参数和返回值

这里我们要求一个节点 偷与不偷的两个状态所得到的金钱，那么返回值就是一个长度为2的数组。其实这里的返回数组就是dp数组,所以dp数组（dp table）以及下标的含义：下标为0记录不偷该节点所得到的的最大金钱，下标为1记录偷该节点所得到的的最大金钱。

* 确定终止条件

在遍历的过程中，如果遇到空间点的话，很明显，无论偷还是不偷都是0，所以就返回

* 确定遍历顺序

首先明确的是使用后序遍历。因为通过递归函数的返回值来做下一步计算。通过递归左节点，得到左节点偷与不偷的金钱。通过递归右节点，得到右节点偷与不偷的金钱。

* 确定单层递归的逻辑
    * 如果是偷当前节点，那么左右孩子就不能偷，val1 = cur->val + left[0] + right[0];
    * 如果不偷当前节点，那么左右孩子就可以偷，至于到底偷不偷一定是选一个最大的，所以：val2 = max(left[0], left[1]) + max(right[0], right[1]);
    * 最后当前节点的状态就是{val2, val1}; 即：{不偷当前节点得到的最大金钱，偷当前节点得到的最大金钱}
```java
class Solution {
    public int rob(TreeNode root) {
        int[] dp = new int[2];
        dp = robTree(root);
        return Math.max(dp[0],dp[1]);
    }
    public int[] robTree(TreeNode root){
        if(root == null) return new int[]{0,0};
        // 下标0：不偷  下标1：偷
        int[] left = robTree(root.left);
        int[] right = robTree(root.right);
        // 偷当前节点,则子节点不能偷
        int val1 = root.val + left[0] + right[0];
        // 不偷当前节点，那么子节点可偷可不偷，就需要分别从左右孩子中选择一个收益最大的
        int val2 = Math.max(left[0],left[1]) + Math.max(right[0], right[1]);
        // 返回偷与不偷当前节点的收益
        return new int[]{val2,val1};
    }
}
```
时间复杂度：O(n)
空间复杂度：O(n)

