## 题目

给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。

**示例 1**:

>输入: amount = 5, coins = [1, 2, 5]
>输出: 4
>解释: 有四种方式可以凑成总金额:
>5=5
>5=2+2+1
>5=2+1+1+1
>5=1+1+1+1+1

**示例 2**:

>输入: amount = 3, coins = [2]
>输出: 0
>解释: 只用面额2的硬币不能凑成总金额3。

**示例 3**:

>输入: amount = 10, coins = [10]
>输出: 1

**注意**:

你可以假设：

* 0 <= amount (总金额) <= 5000
* 1 <= coin (硬币面额) <= 5000
* 硬币种类不超过 500 种
* 结果符合 32 位符号整数

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/coin-change-2](https://leetcode-cn.com/problems/coin-change-2?fileGuid=RqJhrKqHJDhyVqyp)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：动态规划

这是一个完全背包问题，可以用动态规划来解决，但是跟完全背包问题相比，本题又稍有不同：纯完全背包是能否凑成总金额，而本题是要求凑成总金额的组合数（组合不强调元素之间的顺序，排列强调元素之间的顺序）。

* 首先确定dp数组的含义

dp[j]：凑成总金额j的货币组合数为dp[j]

* 确定递推公式

dp[j] （考虑coins[i]的组合总和） 就是所有的dp[j - coins[i]]（不考虑coins[i]）相加。所以递推公式：dp[j] += dp[j - coins[i]];

* dp数组初始化

首先dp[0]一定要为1，dp[0] = 1是 递归公式的基础。从dp[i]的含义上来讲就是，凑成总金额0的货币组合数为1（也就是空集合，空集也算一个）。下标非0的dp[j]初始化为0，这样累计加dp[j - coins[i]]的时候才不会影响真正的dp[j]

* 确定遍历顺序

本题只能先遍历物品后遍历背包，这种遍历顺序相当于求组合数；先遍历背包后遍历物品相当于求排列数

```java
class Solution {
    public int change(int amount, int[] coins) {
        // if(amount == 0) return 0;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        // TODO：先背包后物品计算的是排列数
        // 先遍历背包
        // for(int j = 0; j <= amount; j++){
        //     // 后遍历物品
        //     for(int i = 0; i < coins.length; i++){
        //         if(j - coins[i] >= 0){
        //             dp[j] += dp[j - coins[i]];
        //         }
        //     }
        // }
        // TODO:先物品后背包计算的是 组合数
        // 先遍历物品
        for(int i = 0; i < coins.length; i++){
            // 后遍历背包
            for(int j = coins[i]; j <= amount; j++){
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
```
时间复杂度：O(mn)
空间复杂度：O(m)

其中，m是金额总数，n是硬币种类数

