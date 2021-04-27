class Solution {
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        if(len == 1) return nums[0];
        fastSort(nums,0,len -1);
        return nums[len - k];
    }
    public void fastSort(int[] nums, int start, int end){
        if(start >= end) return;
        int key = nums[start];
        int left = start, right = end;
        while(left < right){
            // 内层2个循环一定是要跟key对比
            while(left < right && nums[right] >= key)
                right--;
            nums[left] = nums[right];
            while(left < right && nums[left] <= key)
                left++;
            nums[right] = nums[left];
        }
        nums[left] = key;
        // 一定是递归的
        fastSort(nums,start,left - 1);
        fastSort(nums,right + 1, end);
    }
}
