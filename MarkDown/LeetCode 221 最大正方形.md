## 题目

在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。

**示例 1**：

![图片](https://uploader.shimo.im/f/vad4M3F2G1tL4YYp.png!thumbnail?fileGuid=pjVyPXpw9kwDYgkR)

>输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
>输出：4

**示例 2**：

![图片](https://uploader.shimo.im/f/fQi2NKnjMa1V2f4a.png!thumbnail?fileGuid=pjVyPXpw9kwDYgkR)

>输入：matrix = [["0","1"],["1","0"]]
>输出：1

**示例 3**：

>输入：matrix = [["0"]]
>输出：0

**提示**：

* m == matrix.length
* n == matrix[i].length
* 1 <= m, n <= 300
* matrix[i][j] 为 '0' 或 '1'

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/maximal-square](https://leetcode-cn.com/problems/maximal-square?fileGuid=pjVyPXpw9kwDYgkR)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：动态规划

* 定义dp数组机器下标含义

dp[i][j]：表示以坐标(i,j)为右下角坐标且只包含字符'1'的最大正方形的边长

* 确定dp[i][j]的递推公式
    * 如果matrix[i][j]是字符'0'，那么以(i,j)为右下角的正方形中不全为字符'1'，不满足要求，所以dp[i][j] = 0；
    * 如果matrix[i][j] == ‘1’，则dp[i][j]的值取决于三个位置的值分别是(i,j)的上方、左边和斜上方三者中最小值加1，也就是dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
* 如何初始化dp数组

初始化dp数组主要考虑第一行和第一列，如果matrix第一行或第一列中某个元素的值是字符'1'，那么该位置对应的dp的值就是1，否则就是0，因为第一行和第一列最大只能组成边长为1的正方形。

* 确定遍历顺序

由递推公式可以看出需要从上到下哦，从左到右

```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0; // 记录全为'1'的正方形最大边长
        // 初始化第一行
        for(int j = 0; j < n; j++){
            if(matrix[0][j] == '1'){
                max = 1;
                dp[0][j] = 1;
            }
        }
        // 初始化第一列
        for(int i = 0; i < m; i++){
            if(matrix[i][0] == '1'){
                max = 1;
                dp[i][0] = 1;
            }
        }
        // 开始dp
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == '1'){
                    // dp[i][j]取决于3个点，分别是[i,j]正上方、正左方和斜上角
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
                // 更新最大边长
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }
}
```
时间复杂度：O(mn)
空间复杂度：O(mn)

