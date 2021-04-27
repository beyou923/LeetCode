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
