//简单版 易理解
class Solution1 {
    public int[] countBits(int num) {
        if(num == 0) return new int[]{0};
        if(num == 1) return new int[]{0,1};
        if(num == 2) return new int[]{0,1,1};

        int[] dp = new int[num + 1];
        dp[0] = 0;
        dp[1] = 1;
        int i = 1;
        int y = (int)Math.pow(2,i);

        for(int x = 2; x <= num && x <= (int)Math.pow(2,i + 1); x++){
            if(x == y) dp[x] = 1;

            if(x == (int)Math.pow(2,i + 1)){
                dp[x] = 1;
                i++;
                y = (int)Math.pow(2,i);
            }

            dp[x] = dp[x - y] + 1;
        }
        return dp;
    }
}
// 优化版 效率高
class Solution {
    public int[] countBits(int num) {

        int[] dp = new int[num + 1];
        int highBit = 0;
        for(int i = 1; i <= num; i++){
            // 判断i是不是2的整数次幂
            if((i & (i -1)) == 0){
                highBit = i;
            }
            dp[i] = dp[i - highBit] + 1;
        }
        return dp;
    }
}