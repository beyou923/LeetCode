class Solution {
    public int search(int[] nums, int target) {
        int len = nums.length;
        int low = 0, hight = len -1;
        while(low <= hight){
            int mid = low + (hight - low) / 2;
            if(nums[mid] == target)
                return mid;

            if(nums[mid] < nums[hight]){
                if(nums[mid] < target && target <= nums[hight]){
                    low = mid + 1;
                }else{
                    hight = mid -1;
                }
            } else{
                if(nums[low] <= target && target < nums[mid]){
                    hight = mid -1;
                } else{
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}
