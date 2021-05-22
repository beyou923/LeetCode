// 回溯法
class Solution1 {
    public boolean isMatch(String s, String p) {
        if(p.isEmpty()) return s.isEmpty();
        // 比较第一个字符是否相等
        boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        // 如果下一个字符是 *
        if (p.length() >= 2 && p.charAt(1) == '*'){
            // TODO: isMatch(s,p.substring(2))表示p中第0个字符没有出现在s中
            // TODO: firstMatch && isMatch(s.substring(1),p) 表示p中第0个字符在s中出现了1次或多次
            return isMatch(s,p.substring(2)) || (firstMatch && isMatch(s.substring(1),p));
        }else {
            // 一般情况
            return firstMatch && isMatch(s.substring(1),p.substring(1));
        }
    }
}
// 动态规划
class Solution2 {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        // 初始化dp数组
        dp[0][0] = true;
        for(int j = 2; j <= n; j++)
            dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(p.charAt(j) == '*'){
                    // p中第j个位置为 * 需要考虑两种情况
                    // 情况1：p中 * 前面的字符没有在s中出现 --> 保持s不变，剪掉p中前2个字符 dp[i + 1][j - 1]
                    // 情况2：p中 * 前面的字符与在s中出现了 --> 保持p不变，剪掉s中前一个字符 firstMatch(s,p,i,j - 1) && dp[i][j + 1]
                    dp[i + 1][j + 1] = dp[i + 1][j - 1] || firstMatch(s,p,i,j - 1) && dp[i][j + 1];
                } else{
                    // 一般情况
                    dp[i + 1][j + 1] = firstMatch(s,p,i,j) && dp[i][j];
                }
            }
        }
        return dp[m][n];
    }
    // 比较s和p中两个指定位置的字符是否匹配
    public boolean firstMatch(String s, String p, int i , int j){
        return s.charAt(i) == p.charAt(j) || p.charAt(j) == '.';
    }
}