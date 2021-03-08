/*
*   假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？注意：给定 n 是一个正整数。
*   示例 1：
*       输入： 2
*       输出： 2
*       解释： 有两种方法可以爬到楼顶。
*           1.  1 阶 + 1 阶
*           2.  2 阶
*   示例 2：
*       输入： 3
*       输出： 3
*       解释： 有三种方法可以爬到楼顶。
*           1.  1 阶 + 1 阶 + 1 阶
*           2.  1 阶 + 2 阶
*           3.  2 阶 + 1 阶
* 链接：https://leetcode-cn.com/problems/climbing-stairs
* */
public class Solution {
    public int climbStairs(int n) {
        if (n <= 2)
            return n;
        int dp[] = new int[n+1];
        dp[1] = 1; // 达到第一节台阶只有1种走法
        dp[2] = 2; // 达到第二节台阶有2种走法
        for (int i = 3; i <= n; ++i) {
            // 到达地i个台阶的走法等于达到地 i-1个台阶的走法和到到第 i-2 个台阶的走法之和
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n]; // 到达第n节台阶走法
    }
}
