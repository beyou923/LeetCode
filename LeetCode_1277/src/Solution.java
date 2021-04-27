class Solution {
    public int countSquares(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int result = 0;
        // 初始化dp数组第一行
        for(int j = 0; j < n; j++){
            if(matrix[0][j] == 1){
                dp[0][j] = 1;
                result++;
            }
        }
        // 初始化dp数组第一列
        for(int i = 1; i < m; i++){
            if(matrix[i][0] == 1){
                dp[i][0] = 1;
                result++;
            }
        }
        // 开始dp
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == 1){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) +1;
                }
                // 累加dp中的值就是答案
                result += dp[i][j];
            }
        }
        return result;
    }
}
