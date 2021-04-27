class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        // 先遍历物品
        for(String str : strs){
            int zeroNum = 0, oneNum = 0;
            // 统计单个字符串中 0/1 的个数
            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) == '0') zeroNum++;
                else oneNum++;
            }
            // 倒叙遍历背包
            for(int i = m; i >= zeroNum; i--){
                for(int j = n; j >= oneNum; j--){
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
