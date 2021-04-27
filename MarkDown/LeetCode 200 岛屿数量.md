## 题目

给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。此外，你可以假设该网格的四条边均被水包围。

**示例 1**：

>输入：grid = [
>["1","1","1","1","0"],
>["1","1","0","1","0"],
>["1","1","0","0","0"],
>["0","0","0","0","0"]
>]
>输出：1

**示例 2**：

>输入：grid = [
>["1","1","0","0","0"],
>["1","1","0","0","0"],
>["0","0","1","0","0"],
>["0","0","0","1","1"]
>]
>输出：3



**提示**：

* m == grid.length
* n == grid[i].length
* 1 <= m, n <= 300
* grid[i][j] 的值为 '0' 或 '1'

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/number-of-islands](https://leetcode-cn.com/problems/number-of-islands?fileGuid=kQjjXHJWGW38Dpw9)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：DFS

遍历网格中的每一个格子，如果值为'1'，将res值加1，将其值变为0表示已遍历过，以该位置为起点，朝4个方向（上下左右）检查相邻的位置是否为1，如果为1将相邻位置变为0

```java
class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length < 1) return 0;
        int m = grid.length, n = grid[0].length;
        int res = 0;
        for(int row = 0; row < m; row++){
            for(int col = 0; col < n; col++){
                if(grid[row][col] == '1'){
                    res++;
                    dfs(grid,row,col);
                }
            }
        }
        return res;
    }
    public void dfs(char[][] grid, int row, int col){
        int m = grid.length, n = grid[0].length;
        if(row < 0 || col < 0 || row >= m || col >= n || grid[row][col] == '0')
            return;
        grid[row][col] = '0';
        // 已（row, col）为起点，将4个方向上的1变为0
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }
}
```
时间复杂度：O(mn)
空间复杂度：O(mn)

