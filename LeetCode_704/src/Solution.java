public class Solution {
    public static int search(int[] nums, int target){
        int len = nums.length;
        if (len < 1) return -1;
        int start = 0, end = len -1;
        while (start <= end){
            int mid = (start + end) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) end = mid -1;
            else start = mid + 1;
        }
        return -1;
    }

    public static void main(String[] args){
        int[] nums = new int[]{-1,0,3,5,9,12};
        int res = search(nums,9);
        System.out.println(res);
    }
}
