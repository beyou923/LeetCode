## 题目

编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

* 每行的元素从左到右升序排列。
* 每列的元素从上到下升序排列。

**示例 1**：

![图片](https://uploader.shimo.im/f/ARoBpjucebRZJ2hl.png!thumbnail?fileGuid=Cg9qtDjpGgywJKpP)

>输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
>输出：true

**示例 2**：

![图片](https://uploader.shimo.im/f/AJrxxkUVwViIoLsE.png!thumbnail?fileGuid=Cg9qtDjpGgywJKpP)

>输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
>输出：false

**提示**：

* m == matrix.length
* n == matrix[i].length
* 1 <= n, m <= 300
* -10^9 <= matix[i][j] <= 10^9
* 每行的所有元素从左到右升序排列
* 每列的所有元素从上到下升序排列
* -10^9 <= target <= 10^9

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/search-a-2d-matrix-ii](https://leetcode-cn.com/problems/search-a-2d-matrix-ii?fileGuid=Cg9qtDjpGgywJKpP)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

本题与[《剑指offer》第4题](https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof?fileGuid=Cg9qtDjpGgywJKpP)以及[LeetCode 74](https://leetcode-cn.com/problems/search-a-2d-matrix/?fileGuid=Cg9qtDjpGgywJKpP)题一样，每次用target与右上角或左下角元素比较，逐渐缩小搜索范围

### 方法一：与右上角元素比较

每次与右上角元素比较有3种可能，分别是

* target == matrix[i][j] ,直接返回true
* target > matrix[i][j] ， 舍弃当前行，因为比较target大的元素都在右上角元素的下方
* target < matrix[i][j] ， 舍弃当前列，因为比较target小的元素都在右上角元素的左边

上面matrix[i][j]表示右上角元素

```java
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // 每次与右上角的元素比较
        // TODO: 大于target的元素都在下方，小于target的元素都在左边
        for(int i = 0; i < m; i++){
            for(int j = n - 1; j >= 0;){
                if(matrix[i][j] == target){
                    return true;
                } else if(matrix[i][j] > target){
                    // 舍弃最后一列
                    j--;
                } else{
                    // 跳出当前循环，舍弃最上面一行 
                    break;
                }
            }
        }
        return false;
    }
}
```
### 方法二：与左下角元素比较

每次与左下角元素比较有3种可能，分别是

* target == matrix[i][j] ,直接返回true
* target > matrix[i][j] ， 舍弃当前列，因为比较target大的元素都在左下角元素的右边
* target < matrix[i][j] ， 舍弃当前行，因为比较target小的元素都在左下角元素的上边

上面matrix[i][j]表示左下角元素

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // TODO: 每次与最小角元素比较，如果target == 左下角元素，直接返回
        // TODO: 如果target > 左下角元素,舍弃当前列（大的元素都在右边）
        // TODO: 如果target < 左下角元素,舍弃当前行（大的元素都在上边）
        for(int i = m -1; i >= 0; i--){
            for(int j = 0; j < n;){
                if(matrix[i][j] == target){
                    return true;
                } else if(matrix[i][j] > target){
                    break;
                }else{
                    j++;
                }
            }
        }
        return false;
    }
}
```
上面两种方法的本质是一样的，都是利用二维数组从左到右、从上到下是递增的特性缩小搜索空间求解，它们的时间复杂度和空间复杂度都是相同的
时间复杂度：O(m+n)

空间复杂度：O(1)

