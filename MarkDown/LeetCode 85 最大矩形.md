## 题目

给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

**示例 1**：

![图片](https://uploader.shimo.im/f/OB0EA32mNZ6zFJwp.png!thumbnail?fileGuid=86TRV8tDv8PtH6yQ)

>输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
>输出：6
>解释：最大矩形如上图所示。

**示例 2**：

>输入：matrix = []
>输出：0

**示例 3**：

>输入：matrix = [["0"]]
>输出：0

**示例 4**：

>输入：matrix = [["1"]]
>输出：1

**示例 5**：

>输入：matrix = [["0","0"]]
>输出：0

**提示**：

* rows == matrix.length
* cols == matrix[0].length
* 0 <= row, cols <= 200
* matrix[i][j] 为 '0' 或 '1'

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/maximal-rectangle](https://leetcode-cn.com/problems/maximal-rectangle?fileGuid=86TRV8tDv8PtH6yQ)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 方法一：暴力破解

最原始地，我们可以列举每个可能的矩形。我们枚举矩形所有可能的左上角坐标和右下角坐标，并检查该矩形是否符合要求。然而该方法的时间复杂度过高，不能通过所有的测试用例，因此我们必须寻找其他方法。

* 首先计算出矩阵的每个元素的左边(包括该元素)连续 1 的数量，使用二维数组 left 记录，其中 left[i][j] 为矩阵第 i 行第 j 列元素的左边连续 1 的数量。
* 随后，对于矩阵中任意一个点，我们枚举以该点为右下角的全 1 矩形。具体而言，当考察以 matrix[i][j] 为右下角的矩形时，我们枚举满足 0≤k≤i 的所有可能的 k，此时矩阵的最大宽度就为min(left[i][j],left[i−1][j],…,left[k][j])

对每个点重复这一过程，就可以得到全局的最大矩形。我们预计算最大宽度的方法事实上将输入转化成了一系列的柱状图，我们针对每个柱状图计算最大面积。

```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int max = 0;
        int[][] left = new int[m][n];
        // step 1：统计位置[i,j]为1是其左边（包括位置[i,j]连续1的个数）
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '1'){
                    left[i][j] = (j == 0 ? 0 : left[i][j-1]) + 1;
                }
            }
        }
        // step 2：选择以[i,j]为右下角的矩形的最大宽度
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '0')
                    continue;
                int width = left[i][j];
                int area = width;
                for(int k = i - 1; k >= 0; k--){
                    width = Math.min(width, left[k][j]);
                    area = Math.max(area, width * (i - k + 1));
                }
                max = Math.max(area, max);
            }
        }
        return max;
    }
}
```
时间复杂度：O(m^2n)
空间复杂度：O(mn)

### 方法二：单调栈

在方法一中，我们讨论了将输入拆分成一系列的柱状图。为了计算矩形的最大面积，我们只需要计算每个柱状图中的最大面积，并找到全局最大值。

我们可以使用「[84. 柱状图中最大的矩形的官方题解](https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/zhu-zhuang-tu-zhong-zui-da-de-ju-xing-by-leetcode-/?fileGuid=86TRV8tDv8PtH6yQ)」中的单调栈的做法，将其应用在我们生成的柱状图中。

```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int max = 0;
        int[][] left = new int[m][n];
        // step 1：统计位置[i,j]为1是其左边（包括位置[i,j]连续1的个数）
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '1'){
                    left[i][j] = (j == 0 ? 0 : left[i][j-1]) + 1;
                }
            }
        }
        
        for(int j = 0; j < n; j++){
            int[] start = new int[m];
            int[] end = new int[m];
            Deque<Integer> stack = new LinkedList<>();
            for(int i = 0; i < m ;i++){
                while(!stack.isEmpty() && left[stack.peek()][j] >= left[i][j])
                    stack.pop();
                
                start[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear(); // 这一句不可或缺
            for(int i = m -1; i >= 0; i--){
                while(!stack.isEmpty() && left[stack.peek()][j] >= left[i][j])
                    stack.pop();
                end[i] = stack.isEmpty() ? m : stack.peek();
                stack.push(i);
            }
           
            for(int i = 0; i < m; i++){
                int h = end[i] - start[i] - 1;
                max = Math.max(max, h * left[i][j]);
            }
        }
        return max;
    }
}
```
时间复杂度：O(mn)
空间复杂度：O(mn)

