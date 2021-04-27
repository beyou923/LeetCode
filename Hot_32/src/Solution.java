import java.util.Arrays;
class Solution {
    public int longestValidParentheses(String s) {
        int result = 0;
        int[] dp = new int[s.length()];
        Arrays.fill(dp,0);
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) == ')'){
                // 如果i-1是（，那么我们判断i-2
                if(s.charAt(i-1) == '('){
                    if(i > 1) dp[i] = dp[i -2] + 2;
                    else dp[i] = 2;
                }
                // 如果i-1也是)，我们需要继续往前判断
                else if (i - dp[i -1] > 0 && s.charAt(i - dp[i - 1] - 1) == '('){
                    if(i - dp[i - 1] - 2 >= 0)
                        dp[i] = 2 + dp[i - 1] + dp[i - dp[i - 1] - 2];
                    else
                        dp[i] = 2 + dp[i - 1];
                }
            }
        }
        // 找dp中最大值
        for (int num:dp) {
            if (result < num)
                result = num;
        }
        return result;
    }
}