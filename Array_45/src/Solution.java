/*
*   给定一个非负整数数组，你最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。你的目标是使用最少的跳跃次数到达数组的最后一个位置。
*   示例:
*       输入: [2,3,1,1,4]
*       输出: 2
*       解释: 跳到最后一个位置的最小跳跃数是 2。从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。
*   说明:假设你总是可以到达数组的最后一个位置。
*
*   链接：https://leetcode-cn.com/problems/jump-game-ii
* */
class Solution {
    public int jump(int[] nums) {
        int end = 0; // 每一段的右边界
        int maxPosition = 0; //每一段最远能到的位置
        int step = 0; // 当前步数
        int len = nums.length;
        for (int i = 0; i < len -1; ++i){
            maxPosition = Math.max(maxPosition,i + nums[i]);// 更新最远能到的位置
            if (i == end){
                end = maxPosition;// 到达当前段的右边界，更新为下一段的右边界
                step++;//每一段必须跳一步
            }
        }
        return step;
    }
}