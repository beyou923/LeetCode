public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
        // 求无序（降序）区间的最小值
        for(int i = 1; i < nums.length ; i++){
            if(nums[i] < nums[i - 1])
                flag = true; // 降序
            if(flag == true)
                min = Math.min(min, nums[i]);
        }
        flag = false;
        // 求无序(降序)区间的最大值
        for(int j = nums.length - 2; j >= 0; j--){
            if(nums[j] > nums[j + 1])
                flag = true;
            if(flag == true)
                max = Math.max(max, nums[j]);
        }
        int start = 0, end = 0;
        // 找无序区间的起始位置, 哪个位置是无序区间的起始位置？
        // TODO: 第一个大于无序区间最小值的元素所在的位置
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > min){
                start = i;
                break;
            }
        }
        // 找无需区间的结束位置，哪个位置是无序区间的结束位置？
        // TODO: 第一个小于无序区间最大值的元素所在的位置
        for(int j = nums.length - 1; j >= 0; j--){
            if(nums[j] < max){
                end = j;
                break;
            }
        }
        return end - start <= 0 ? 0 : end -start + 1;
    }
}
