public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        int sum = 0; // 连继子数组的和
        int i = 0; // 连续子数组的起始位置
        int subLength = 0; // 连续子数组的长度
        for(int j = 0; j < nums.length; j++){
            sum += nums[j];
            while(sum >= target){
                subLength = j -i + 1; // 当前最短连续子数组的长度
                result = Math.min(result, subLength);
                sum -= nums[i++]; // 改变最短子数组的起始位置 
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
