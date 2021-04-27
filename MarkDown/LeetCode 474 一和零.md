## 题目

给你一个二进制字符串数组 strs 和两个整数 m 和 n 。请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。

**示例 1**：

>输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
>输出：4
>解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
>其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。

**示例 2**：

>输入：strs = ["10", "0", "1"], m = 1, n = 1
>输出：2
>解释：最大的子集是 {"0", "1"} ，所以答案是 2 。

**提示**：

* 1 <= strs.length <= 600
* 1 <= strs[i].length <= 100
* strs[i] 仅由 '0' 和 '1' 组成
* 1 <= m, n <= 100

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/ones-and-zeroes](https://leetcode-cn.com/problems/ones-and-zeroes?fileGuid=QVrk9yxRP6VjX8WW)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：动态规划

这其实是一道0/1背包问题，只是跟一般的背包问题有点区别，一般背包只有一维，这里是二维。0和1个数m,n可以看成是背包，strs数组中的元素可以看成是物品（每个商品只有一份），元素中0和1的个数可以看成是物品的价值。

根据上面的分析可以用动态规划来解决这个问题，首先定义dp数组的含义。

dp[i][j]：最多有i个0和j个1的strs的最大子集的大小为dp[i][j]。dp[i][j] 可以由前一个strs里的字符串推导出来，strs里的字符串有zeroNum个0，oneNum个1。dp[i][j] 就可以是 dp[i - zeroNum][j - oneNum] + 1。然后我们在遍历的过程中，取dp[i][j]的最大值。所以递推公式：dp[i][j] = max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1)。dp[i][j]可以初始化为全0，按照0/1背包问题需要先遍历物品后倒叙遍历背包

```java
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        // 先遍历物品
        for(String str : strs){
            int zeroNum = 0, oneNum = 0;
            // 统计单个字符串中 0/1 的个数
            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) == '0') zeroNum++;
                else oneNum++;
            }
            // 倒叙遍历背包
            for(int i = m; i >= zeroNum; i--){
                for(int j = n; j >= oneNum; j--){
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
```
时间复杂度：O(mnl)O(mnl)，其中 ll 是字符串的个数。
空间复杂度：O(mn)O(mn)。

