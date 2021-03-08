/*
*   给定一个非负整数数组，你最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。判断你是否能够到达最后一个位置。
*   示例1:
*       输入: [2,3,1,1,4]
*       输出: true
*       解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
*   示例2:
*       输入: [3,2,1,0,4]
*       输出: false
*       解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
*
*   链接：https://leetcode-cn.com/problems/jump-game
* */

//贪心算法
class Solution {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        // 数组长度低于2 必然可以到达最后一个位置
        if (len <= 1)
            return true;
        //最远能达到的长度
        int rightMost = 0;
        for (int i = 0; i < len;i++) {
            if (i <= rightMost){
                // 更新最远能到达的长度
                rightMost = Math.max(rightMost,i+nums[i]);
            }
            // 最远能到达的长度大于等于最大索引必然可以达到最后一个位置
            if (rightMost >= len-1)
                return true;
        }
        return false;
    }
}