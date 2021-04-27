## 题目

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。问总共有多少条不同的路径？

**示例 1**：

![图片](https://uploader.shimo.im/f/vU4I6JOdTI6L6aow.png!thumbnail?fileGuid=CwqcTJTrdJJCKx3V)

>输入：m = 3, n = 7
>输出：28

**示例 2**：

>输入：m = 3, n = 2
>输出：3
>解释：
>从左上角开始，总共有 3 条路径可以到达右下角。
>1. 向右 -> 向下 -> 向下
>2. 向下 -> 向下 -> 向右
>3. 向下 -> 向右 -> 向下

示例 3：

>输入：m = 7, n = 3
>输出：28

示例 4：

>输入：m = 3, n = 3
>输出：6

**提示**：

* 1 <= m, n <= 100
* 题目数据保证答案小于等于 2 * 10^9

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/unique-paths](https://leetcode-cn.com/problems/unique-paths?fileGuid=CwqcTJTrdJJCKx3V)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

动态规划。到达第一行和第一列的路径只有1条；到达除第一行、第一列外的格子[i,j]的路径数等于dp[i,j] = dp[i-1][j] + dp[i][j-1]

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i < n; i++)
            dp[0][i] = 1;
        for(int j = 0; j < m; j++)
            dp[j][0] = 1;
        for(int row = 1; row < m; row++){
            for(int col = 1; col < n; col++){
                dp[row][col] = dp[row -1][col] + dp[row][col - 1];
            }
        }
        return dp[m-1][n-1];
    }
}
```
**时间复杂度**：O(mn)
**空间复杂度**：O(mn)

