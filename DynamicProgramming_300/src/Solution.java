/*
*   给定一个无序的整数数组，找到其中最长上升子序列的长度。
*   示例:
*       输入: [10,9,2,5,3,7,101,18]
*       输出: 4
*       解释: 最长的上升子序列是[2,3,7,101]，它的长度是 4。
*   说明:
*       可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
*       你算法的时间复杂度应该为O(n2) 。
*   进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
*
* 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
* */
/*
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;

        int dp[] = new int[n];

        dp[0] = 1;// 初始转态
        int lis = 1;// 最长上升子序列 只要数组不为空，最长上升子序列至少是 1
        for (int i = 1; i < n; ++i) {
            dp[i]  = 1;// 一开始认为新遍历到的元素独立成一个子序列
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
            // 在dp数组中找一个最大值
            lis = Math.max(lis,dp[i]);
        }
        return lis;
    }
}
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
}