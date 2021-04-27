class Solution {
    public int change(int amount, int[] coins) {
        // if(amount == 0) return 0;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        // TODO：先背包后物品计算的是排列数
        // 先遍历背包
        // for(int j = 0; j <= amount; j++){
        //     // 后遍历物品
        //     for(int i = 0; i < coins.length; i++){
        //         if(j - coins[i] >= 0){
        //             dp[j] += dp[j - coins[i]];
        //         }
        //     }
        // }
        // TODO:先物品后背包计算的是 组合数
        // 先遍历物品
        for(int i = 0; i < coins.length; i++){
            // 后遍历背包
            for(int j = coins[i]; j <= amount; j++){
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
