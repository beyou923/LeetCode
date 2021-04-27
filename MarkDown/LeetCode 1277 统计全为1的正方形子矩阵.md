## 题目

给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。

**示例 1**：

>输入：matrix =
>[
>[0,1,1,1],
>[1,1,1,1],
>[0,1,1,1]
>]
>输出：15
>解释：
>边长为 1 的正方形有 10 个。
>边长为 2 的正方形有 4 个。
>边长为 3 的正方形有 1 个。
>正方形的总数 = 10 + 4 + 1 = 15.

**示例 2**：

>输入：matrix =
>[
>[1,0,1],
>[1,1,0],
>[1,1,0]
>]
>输出：7
>解释：
>边长为 1 的正方形有 6 个。
>边长为 2 的正方形有 1 个。
>正方形的总数 = 6 + 1 = 7.

**提示**：

* 1 <= arr.length <= 300
* 1 <= arr[0].length <= 300
* 0 <= arr[i][j] <= 1

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones](https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones?fileGuid=CWQw8gjp693yKYPc)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：动态规划

本题和[LeetCode 221](https://leetcode-cn.com/problems/maximal-square/?fileGuid=CWQw8gjp693yKYPc)方法一样

* 定义dp数组机器下标含义

dp[i][j] = x ：表示以坐标(i,j)为右下角坐标且只包含字符'1'的最大正方形的边长为x，同时，也表示以 (i, j) 为右下角的正方形的数目为 x（即边长为 1, 2, ..., x 的正方形各一个）。在计算出所有的 dp[i][j] 后，我们将它们进行累加，就可以得到矩阵中正方形的数目。

* 确定dp[i][j]的递推公式
    * 如果matrix[i][j]是字符'0'，那么以(i,j)为右下角的正方形中不全为字符'1'，不满足要求，所以dp[i][j] = 0；
    * 如果matrix[i][j] == ‘1’，则dp[i][j]的值取决于三个位置的值分别是(i,j)的上方、左边和斜上方三者中最小值加1，也就是dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
* 如何初始化dp数组

初始化dp数组主要考虑第一行和第一列，如果matrix第一行或第一列中某个元素的值是字符'1'，那么该位置对应的dp的值就是1，否则就是0，因为第一行和第一列最大只能组成边长为1的正方形。

* 确定遍历顺序

由递推公式可以看出需要从上到下哦，从左到右

```java
class Solution {
    public int countSquares(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int result = 0;
        // 初始化dp数组第一行
        for(int j = 0; j < n; j++){
            if(matrix[0][j] == 1){
                dp[0][j] = 1;
                result++;
            }
        }
        // 初始化dp数组第一列
        for(int i = 1; i < m; i++){
            if(matrix[i][0] == 1){
                dp[i][0] = 1;
                result++;
            }
        }
        // 开始dp
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == 1){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) +1;
                }
                // 累加dp中的值就是答案
                result += dp[i][j];
            }
        }
        return result;
    }
}
```
时间复杂度：O(mn)
空间复杂度：O(mn)

