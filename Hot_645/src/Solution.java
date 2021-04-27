class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] result = new int[2];
        for(int num : nums){
            int index = Math.abs(num) - 1;
            if(nums[index] < 0){
                // 重复出现的数字
                result[0] = index + 1;
            } else{
                nums[index] = -nums[index];
            }
        }
        // 寻找消失的数字
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0)
                result[1] = i + 1;
        }
        return result;
    }
}
