## 题目描述

请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。

* 数字 1-9 在每一行只能出现一次。
* 数字 1-9 在每一列只能出现一次。
* 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）

数独部分空格内已填入了数字，空白格用 '.' 表示。

**注意**：

* 一个有效的数独（部分已被填充）不一定是可解的。
* 只需要根据以上规则，验证已经填入的数字是否有效即可。

示例 1：

![图片](https://uploader.shimo.im/f/0WA7uArb5VlKjC85.png!thumbnail?fileGuid=wTvGdGKc8r9xqrRy)

>输入：board =
>[["5","3",".",".","7",".",".",".","."]
>,["6",".",".","1","9","5",".",".","."]
>,[".","9","8",".",".",".",".","6","."]
>,["8",".",".",".","6",".",".",".","3"]
>,["4",".",".","8",".","3",".",".","1"]
>,["7",".",".",".","2",".",".",".","6"]
>,[".","6",".",".",".",".","2","8","."]
>,[".",".",".","4","1","9",".",".","5"]
>,[".",".",".",".","8",".",".","7","9"]]
>输出：true

**示例 2**：

>输入：board =
>[["8","3",".",".","7",".",".",".","."]
>,["6",".",".","1","9","5",".",".","."]
>,[".","9","8",".",".",".",".","6","."]
>,["8",".",".",".","6",".",".",".","3"]
>,["4",".",".","8",".","3",".",".","1"]
>,["7",".",".",".","2",".",".",".","6"]
>,[".","6",".",".",".",".","2","8","."]
>,[".",".",".","4","1","9",".",".","5"]
>,[".",".",".",".","8",".",".","7","9"]]
>输出：false
>解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。

**提示**：

* board.length == 9
* board[i].length == 9
* board[i][j] 是一位数字或者 '.'

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/valid-sudoku](https://leetcode-cn.com/problems/valid-sudoku?fileGuid=wTvGdGKc8r9xqrRy)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

3 趟遍历，分别编列每一行、每一列以及每一个3x3 的子网格

注意在遍历 3x3 的子网格时候注意每个子网格行列起始小标的计算

```java
int col = (sign % 3) * 3; // 3 x 3子网格起始列下标
int row = (sign / 3) * 3; // 3 x 3子网格起始行下标
```
其中 0 <= sign <= 8
```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 遍历行
        for(int col = 0; col < board[0].length; col++){
            int[] count = new int[10];
            for(int i = 0; i < board.length; i++){
                int value = board[i][col] - '0';
                if(value >= 1 && value <= 9){
                    if(count[value] != 0) return false;
                    count[value]++;
                }
            }
        }
        // 遍历列
        for(int row = 0; row < board.length; row++){
            int[] count = new int[10];
            for(int j = 0; j < board[0].length; j++){
                int value = board[row][j] - '0';
                if(value >= 1 && value <= 9){
                    if(count[value] != 0)
                        return false;
                    count[value]++;
                }
            }
        }
        // 遍历子数独
        for(int sign = 0; sign < board.length; sign++){
            int col = (sign % 3) * 3; // 3 x 3子网格起始列下标
            int row = (sign / 3) * 3; // 3 x 3子网格起始行下标
            int[] count = new int[10];
            for(int i = col; i < col + 3; i++){
                for(int j = row; j < row + 3; j++){
                    int value = board[i][j] - '0';
                    if(value >= 1 && value <= 9) {
                        if(count[value] != 0) return false;
                        count[value]++;
                    }
                }
            }
        }
        return true;
    }
}
```
时间复杂度：O(1)
空间复杂度：O(1)

