class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        // 遍历背包
        for(int i = 0; i <= n ; i++){
            dp[i] = i; // dp[i]最坏的结果就是由i个1相加
            // 遍历商品
            for(int j = 1; j * j <= i; j++)
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
        }
        return dp[n];
    }
}
