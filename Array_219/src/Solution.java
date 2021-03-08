import java.util.HashMap;
/*
*   给定一个整数数组和一个整数k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
*   示例1:
*       输入: nums = [1,2,3,1], k = 3
*       输出: true
*
* 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
 */
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
        if (len < 2)
            return false;
        // key为元素，value为元素的索引
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        int dev = 0;

        for (int i = 0; i < len; ++i) {
            if (hashMap.containsKey(nums[i])) {
                // dev表示相同元素的索引差
                dev = i - hashMap.get(nums[i]);
                //更新索引  要求|i-j| <= k 在出现相同元素时候，只有更新索引可能找到答案
                hashMap.put(nums[i],i);
                if (dev <= k)
                    return true;
            } else
                hashMap.put(nums[i],i);
        }
        return false;
    }
}