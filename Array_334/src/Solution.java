import javax.xml.stream.XMLInputFactory;

/*
*   给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。数学表达式如下:
*   如果存在这样的 i, j, k,且满足 0 ≤ i < j < k ≤ n-1，使得arr[i] < arr[j] < arr[k] ，返回 true ;否则返回 false 。
*   说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。
*   示例 1:
*       输入: [1,2,3,4,5]
*       输出: true
*   示例 2:
*       输入: [5,4,3,2,1]
*       输出: false
*   注意：序列不需要索引连续
链接：https://leetcode-cn.com/problems/increasing-triplet-subsequence
* */
public class Solution {
    public boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        if (len < 3)
            return false;

        int small = Integer.MAX_VALUE; // 递增子序列中的最小值
        int mid = Integer.MAX_VALUE; // 递增子序列中中间值
        for (int i = 0; i < len; i++) {
            if (nums[i] <= small) {
                // 更新最小值
                small = nums[i];
            } else if (nums[i] <= mid) {
                // 更新中间值
                mid = nums[i];
            } else if (nums[i] > mid){
                // 没有更新说明已经存在3个元素的递增子序列
                return true;
            }
        }
        return false;
    }
}