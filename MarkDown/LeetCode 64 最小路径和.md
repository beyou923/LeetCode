## 题目

给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。说明：每次只能向下或者向右移动一步。

**示例 1**：

![图片](https://uploader.shimo.im/f/hMcoD5NXdhT9Nwrv.png!thumbnail?fileGuid=Ytq6Hqcq3cd9TQPK)

>输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
>输出：7
>解释：因为路径 1→3→1→1→1 的总和最小。

**示例 2**：

>输入：grid = [[1,2,3],[4,5,6]]
>输出：12

**提示**：

* m == grid.length
* n == grid[i].length
* 1 <= m, n <= 200
* 0 <= grid[i][j] <= 100

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/minimum-path-sum](https://leetcode-cn.com/problems/minimum-path-sum?fileGuid=Ytq6Hqcq3cd9TQPK)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：动态规划

到达网格中(i,j)位置的最短路径可以表示为从两个方向（向右/向下移动）到达当前位置路径中较小者加上当前位置元素值，即

dp[i][j] = grid[i][j] + min(dp[i-1][j] , dp[i][j-1])

其中dp[0][i]就是grid中第一行元素累加

dp[0][0] = grid[0][0]

dp[0][i] = dp[0][i-1] + grid[0][i]

dp[j][0]类似

dp[0][0] = grid[0][0]

dp[j][0] = dp[j-1][0] + grid[j][0]

最后，返回dp[m-1][n-1]即可

```java
public class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        // 为dp数组第一行
        for(int i = 0; i < n; i++){
            if(i == 0){
                dp[0][i] = grid[0][i];
            } else{
                dp[0][i] = dp[0][i -1] + grid[0][i];
            }
        }
        // 为dp数组第一列初始化
        for(int i = 0; i < m; i++){
            if(i == 0){
                dp[i][0] = grid[i][0];
            } else{
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }
        }
        // 填充dp数组
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = grid[i][j] + Math.min(dp[i -1][j], dp[i][j -1]);
            }
        }
        return dp[m-1][n-1];
    }
}
```
时间复杂度：O(mn)
空间复杂度：O(mn)

