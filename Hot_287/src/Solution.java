class Solution {
    // 二分查找法
    public int findDuplicate(int[] nums) {
        // 二分查找的初始区间 [1,n-1) 也就是[1,n]
        int left = 1, right = nums.length;
        while(left < right){
            int mid = left + (right - left) / 2, cnt = 0;
            for(int num : nums){
                if(num <= mid) cnt++;
            }

            if(cnt <= mid) left = mid + 1;
            else right = mid;
        }
        return left;
    }
    // 双指针法
    public int findDuplicate1(int[] nums) {
        int slow = 0, fast = 0;
        do{
            // 慢指针移动一步；快指针移动2步
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);
        // 快慢指针相遇后将慢指针指向数组第一个元素，
        // 快慢指针同时移动一步，直到相遇
        slow = 0;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}

