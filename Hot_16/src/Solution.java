import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = Integer.MIN_VALUE;
        int diff = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length - 2; i++){
            int left = i + 1, right = nums.length - 1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(diff > Math.abs(target - sum)) {
                    diff = Math.abs(target - sum);
                    closest = sum;
                }
                if(sum < target){
                    left++;
                    // 跳过重复的nums[left]
                    // TODO：是nums[left] == nums[left - 1] 不是nums[left] == nums[left + 1],进入循环left已加1
                    while(left < right && nums[left] == nums[left - 1])
                        left++;
                }else{
                    right--;
                    // 跳过重复的nums[right]
                    // TODO：是nums[right] == nums[right + 1] 不是nums[right] == nums[right - 1],进入循环left已减1
                    while(left < right && nums[right] == nums[right + 1])
                        right--;
                }

            }
            // 跳过重复的nums[i]
            while(i < nums.length - 2 && nums[i] == nums[i + 1])
                i++;
        }
        return closest;
    }
}
