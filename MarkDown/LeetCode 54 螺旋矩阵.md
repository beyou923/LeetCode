## 题目

给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。

**示例 1**：

![图片](https://uploader.shimo.im/f/RP5xcIhwtF6VWzti.png!thumbnail?fileGuid=PtxvqJWC38gRdvdC)

>输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
>输出：[1,2,3,6,9,8,7,4,5]

**示例 2**：

>输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
>输出：[1,2,3,4,8,12,11,10,9,5,6,7]

**提示**：

* m == matrix.length
* n == matrix[i].length
* 1 <= m, n <= 10
* -100 <= matrix[i][j] <= 100

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/spiral-matrix](https://leetcode-cn.com/problems/spiral-matrix?fileGuid=PtxvqJWC38gRdvdC)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

这个题和《剑指offer》[第29题](https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/submissions/?fileGuid=PtxvqJWC38gRdvdC)一样，思路就是把矩阵分解成若干个圆圈，从外到内依次打印 ，每一圈起点坐标为（0，0）、（1，1）...(i,i) 且rows > start * 2 && cols > start * 2 其中start为每一圈的起点坐标

```java
import java.util.ArrayList;
import java.util.List;
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return result;
        int row = matrix.length;
        int col = matrix[0].length;
        int start = 0; // 每一轮移动的起点 
        while(row > 2 * start && col > 2 * start){
            int endX = col - start -1;
            int endY = row - start -1;
            // 从左往右移动
            if(start <= endX){
                for(int i = start; i <= endX; i++)
                    result.add(matrix[start][i]);
            }
            // 从上到下移动
            if(endY > start ){
                for(int i = start + 1; i <= endY; i++)
                    result.add(matrix[i][endX]);
            }
            //  从右到左移动
            if(endX > start && endY > start){
                for(int i = endX - 1; i >= start; i--)
                    result.add(matrix[endY][i]);
            }
            // 从下到上移动
            if(endX > start && endY - 1 > start){
                for(int i = endY - 1; i > start; i--)
                    result.add(matrix[i][start]);
            }
            start++;
        }
        return result;
    }
}
```
时间复杂度：O(mn)
空间复杂度：O(mn)

