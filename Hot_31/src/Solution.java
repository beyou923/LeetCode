class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while(i >= 0 && nums[i] >= nums[i + 1]) i--;

        if(i >= 0){
            int j = nums.length - 1;
            while( nums[i] >= nums[j]) j--;

            swap(nums, i, j);
        }
        // reverse array
        reverse(nums, i + 1, nums.length - 1);
    }
    // reverse array
    public void reverse(int[] nums, int start, int end){
        if(start >= end) return;
        for(int i = start, j = end; i <= j; i++,j--){
            swap(nums, i ,j);
        }
    }

    public void swap(int[] nums,int a , int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b]= tmp;
    }
}
