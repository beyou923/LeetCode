class Solution {
    // 暴力法
    public int countSubstrings(String s) {
        if(s == null || s.length() < 1) return 0;
        int count = 0;
        for(int start = 0; start < s.length(); start++){
            for(int end = s.length() - 1; end >= start; end--){
                boolean flag = true;
                for(int i = start, j = end; i <= j; i++, j--){
                    if(s.charAt(i) == s.charAt(j)) {
                        continue;
                    } else{
                        flag = false;
                    }
                }
                if(flag == true) count++;
            }
        }
        return count;
    }
    // 动态规划
    public int countSubstrings1(String s) {
        if(s == null || s.length() < 1) return 0;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int count = 0;

        for(int i = len -1; i >= 0 ;i--){
            // 根据dp[i][j]的定义 i <= j 所以循环从j=i开始
            for(int j = i; j < len;j++){
                if(s.charAt(i) == s.charAt(j)){
                    if(j - i <= 1){
                        dp[i][j] = true;
                        count++;
                    } else if(dp[i + 1][j - 1]){
                        dp[i][j] = true;
                        count++;
                    }
                }
            }
        }
        return count;
    }
}