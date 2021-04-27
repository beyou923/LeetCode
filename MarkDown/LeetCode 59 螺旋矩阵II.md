## 题目

给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。

**示例 1**：

![图片](https://uploader.shimo.im/f/40TppYC0dTfvyo2N.png!thumbnail?fileGuid=9W9j8PXx9HPHytVt)

>输入：n = 3
>输出：[[1,2,3],[8,9,4],[7,6,5]]

**示例 2**：

>输入：n = 1
>输出：[[1]]

**提示**：

* 1 <= n <= 20

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/spiral-matrix-ii](https://leetcode-cn.com/problems/spiral-matrix-ii?fileGuid=9W9j8PXx9HPHytVt)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

本题同[LeetCode 54](https://leetcode-cn.com/problems/spiral-matrix?fileGuid=9W9j8PXx9HPHytVt)题以及[《剑指offer》29](https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/submissions/?fileGuid=9W9j8PXx9HPHytVt)题类似，分4个反向填充二维数组即可

```java
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0, right = n -1;
        int up = 0, down = n -1;
        int count = 1;
        while(left <= right && up <= down){
            // 从左到右
            for(int i = left; i <= right; i++)
                matrix[up][i] = count++;
            up++;
            // 从上到小
            for(int i = up; i <= down; i++)
                matrix[i][right] = count++;
            right--;
            // 从右到左
            for(int i = right; i >= left; i--)
                matrix[down][i] = count++;
            down--;
            // 从下到上
            for(int i = down; i >= up; i--)
                matrix[i][left] = count++;
            left++;
        }
        return matrix;
    }
}
```
时间复杂度：O(n*n)
空间复杂度：O(n*n)

