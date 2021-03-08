/*
*   给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）：
*       1. 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
*       2. 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
*
*   输入: [1,2,3,0,2]
*   输出: 3
*   解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
*
*   链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
* */
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0)
            return 0;
        int f0 = -prices[0];// 初始时收益为负
        int f1 = 0;
        int f2 = 0;
        for (int i = 0; i < prices.length; i++){
            int newf0 = Math.max(f0,f2 - prices[i]); // 代表第i天持股
            int newf1 = f0 + prices[i]; // 代表第i天不持股，处于冷冻期
            int newf2 = Math.max(f2,f1); // 代表第i天不持股，处于非冷冻期
            // 更新收益
            f0 = newf0;
            f1 = newf1;
            f2 = newf2;
        }
        return Math.max(f1,f2);
    }
}