## 题目

给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

**示例 1**：

![图片](https://uploader.shimo.im/f/KAU1LmlzQYJW6JF8.png!thumbnail?fileGuid=TGJH89c8k6xwgC9C)

>输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
>输出：true

**示例 2**：

![图片](https://uploader.shimo.im/f/deHPzcUqXkZPnMyB.png!thumbnail?fileGuid=TGJH89c8k6xwgC9C)

>输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
>输出：true

**示例 3**：

![图片](https://uploader.shimo.im/f/i0HCmpuIn3xT9nMe.png!thumbnail?fileGuid=TGJH89c8k6xwgC9C)

>输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
>输出：false

**提示**：

* m == board.length
* n = board[i].length
* 1 <= m, n <= 6
* 1 <= word.length <= 15
* board 和 word 仅由大小写英文字母组成



**进阶**：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/word-search](https://leetcode-cn.com/problems/word-search?fileGuid=TGJH89c8k6xwgC9C)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：DFS

原二维数组就像是一个迷宫，可以上下左右四个方向行走，我们以二维数组中每一个数都作为起点和给定字符串做匹配，我们还需要一个和原数组等大小的 visited 数组，是 bool 型的，用来记录当前位置是否已经被访问过，因为题目要求一个 cell 只能被访问一次。如果二维数组 board 的当前字符和目标字符串 word 对应的字符相等，则对其上下左右四个邻字符分别调用 DFS 的递归函数，只要有一个返回 true，那么就表示可以找到对应的字符串，否则就不能找到

```java
public class Solution {
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length < 1 || board[0].length < 1) return false;
        int m = board.length, n = board[0].length;
        boolean[][] visted = new boolean[m][n];
        // 从board中任意一个位置开始匹配word中的字母
        for(int row = 0; row < m; row++){
            for(int col = 0; col < n; col++){
                if(dfs(board,word,0,row,col,visted))
                    return true;
            }
        }
        return false;
    }
    // 深度优先遍历
    public boolean dfs(char[][] board, String word, int startIndex, int row, int col, boolean[][] visted){
        if(startIndex == word.length()){
            return true;
        }
        int m = board.length, n = board[0].length;
        if ( row < 0 || col < 0 || row >= m || col >= n ||
                board[row][col] != word.charAt(startIndex) ||
                visted[row][col] ) {
            return false;
        }
        visted[row][col] = true;
        // 以[row,col]为起点，从上下左右四个方向遍历，只要有一个方向匹配成功即可
        boolean res = dfs(board,word,startIndex + 1, row - 1,col,visted) ||
                dfs(board,word,startIndex + 1, row + 1,col,visted) ||
                dfs(board,word,startIndex + 1,row, col - 1,visted) ||
                dfs(board,word,startIndex + 1,row,col + 1,visted);
        // 回溯
        visted[row][col] = false;
        return res;
    }
}
```
时间复杂度：O(mn*4^l)
空间复杂度：O(mn)

其中，m，n，l分别是网格的行数、列数以及word长度

