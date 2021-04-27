class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int num : nums){
            //交换律 : a ^ b ^ c ^ a ^ c ^ b ^ d = a ^ a ^ b ^ b ^ c ^ c ^ d = d
            result ^= num; //异或自反律:  0 异或任何数都等于任何数

        }
        return result;
    }
}
