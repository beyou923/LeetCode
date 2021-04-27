class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0; // 记录全为'1'的正方形最大边长
        // 初始化第一行
        for(int j = 0; j < n; j++){
            if(matrix[0][j] == '1'){
                max = 1;
                dp[0][j] = 1;
            }
        }
        // 初始化第一列
        for(int i = 0; i < m; i++){
            if(matrix[i][0] == '1'){
                max = 1;
                dp[i][0] = 1;
            }
        }

        // 开始dp
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == '1'){
                    // dp[i][j]取决于3个点，分别是[i,j]正上方、正左方和斜上角
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
                // 更新最大边长
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }
}
