class Solution {
    public int maxCoins(int[] nums) {
        int len = nums.length;
        if(len < 2) return len == 0 ? 0 : nums[0];
        int[][] dp = new int[len + 2][len + 2];
        int[] val = new int[len + 2];
        // 在原数组首尾增加两个气球，值为1
        val[0] = val[len + 1] = 1;
        for(int i = 1; i <= len; i++)
            val[i] = nums[i -1];

        // start dp
        for(int i = len - 1; i >= 0; i--){
            for(int j = i + 2; j < len + 2; j++){
                for(int k = i + 1; k < j; k++){
                    int sum = val[i] * val[k] * val[j];
                    sum += dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(sum, dp[i][j]);
                }
            }
        }
        return dp[0][len + 1];
    }
}
