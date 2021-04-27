class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length < 1) return new int[]{-1,-1};
        int[] result = new int[]{-1,-1};
        int start = 0, end = nums.length - 1;
        while(start <= end){
            int mid = start + (end - start) / 2;
            if(nums[mid] == target){
                int left = mid;
                int right = mid;
                // 找第一个target出现的左边界
                while(left >= start && nums[left] == target) left--;
                // 找第一个target出现的右边界
                while(right <= end && nums[right] == target) right++;
                result[0] = left + 1;
                result[1] = right - 1;
                break;
            } else if(nums[mid] > target){
                end = mid - 1;
            } else{
                start = mid + 1;
            }
        }
        return result;
    }
}
