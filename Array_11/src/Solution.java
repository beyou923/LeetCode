/*
*   给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为(i,ai) 和 (i, 0)。
*   找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
*   说明：你不能倾斜容器，且 n 的值至少为 2
*   示例：
*       输入：[1,8,6,2,5,4,8,3,7]
*       输出：49
*
*   链接：https://leetcode-cn.com/problems/container-with-most-water
* */

public class Solution {
    public int maxArea(int[] height) {
        int len = height.length;
        if (height == null || len < 2)
            return 0;

        int left = 0; // 左边界
        int right = len -1;// 右边界
        int maxVolume = 0;// 最大容积
        while (left <= right) {
            int length = right - left; // 矩形长
            int width = Math.min(height[left],height[right]); // 矩形宽
            maxVolume = Math.max(length * width,maxVolume); // 容器最大容积
            // 总是移动较小的边界
            if (height[left] <= height[right]){
                left++;
            }else
                right--;

        }
        return maxVolume;
    }
}
