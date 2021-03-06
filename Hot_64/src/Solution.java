public class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        // 为dp数组第一行
        for(int i = 0; i < n; i++){
            if(i == 0){
                dp[0][i] = grid[0][i];
            } else{
                dp[0][i] = dp[0][i -1] + grid[0][i];
            }
        }
        // 为dp数组第一列初始化
        for(int i = 0; i < m; i++){
            if(i == 0){
                dp[i][0] = grid[i][0];
            } else{
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }
        }
        // 填充dp数组
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = grid[i][j] + Math.min(dp[i -1][j], dp[i][j -1]);
            }
        }
        return dp[m-1][n-1];
    }
}
