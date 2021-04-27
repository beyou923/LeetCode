public class Solution {
    // 快速排序法
    public void sortColors(int[] nums) {
        if(nums == null || nums.length < 1) return;
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int left, int right){
        if(left > right) return; // 条件不要写成别的了

        int start = left, end = right;
        int key = nums[start];

        while(start < end){
            while(start < end && nums[end] >= key) end--;
            nums[start] = nums[end];
            while(start < end && nums[start] <= key) start++;
            nums[end] = nums[start];
        }
        nums[start] = key;
        quickSort(nums, left, start -1);
        quickSort(nums, start + 1, right);
    }

    // 单指针法
    public void sortColors1(int[] nums) {
        int ptr = 0;
        // 将所有的0移动到数组头部
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                int tmp = nums[ptr];
                nums[ptr] = nums[i];
                nums[i] = tmp;
                ptr++;
            }
        }
        // 将所有1移动到0的后面
        for(int i = ptr; i < nums.length; i++){
            if(nums[i] == 1){
                int tmp = nums[ptr];
                nums[ptr] = nums[i];
                nums[i] = tmp;
                ptr++;
            }
        }
    }
    // 双指针法
    public void sortColors2(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n -1;
        for(int i = 0; i <= p2; i++){
            while(i <= p2 && nums[i] == 2){
                int tmp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = tmp;
                p2--;
            }
            if(nums[i] == 0){
                int tmp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = tmp;
                p0++;
            }
        }
    }
}
