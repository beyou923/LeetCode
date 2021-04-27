class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        if (len <= 1)
            return nums;
        int[] mulPre = new int[len];
        int[] mutSuf = new int[len];
        int[] output = new int[len];
        // 计算前缀乘积
        for (int i = 0; i < len; i++){
            if (i == 0)
                mulPre[i] = 1;
            else
                mulPre[i] = mulPre[i - 1] * nums[i - 1];
        }
        // 计算后缀乘积
        for (int i = len - 1; i >= 0; i--){
            if (i == len - 1)
                mutSuf[i] = 1;
            else
                mutSuf[i] = nums[i + 1] * mutSuf[i + 1];
        }
        // 填充result
        for (int i = 0; i < len; i++)
            output[i] = mulPre[i] * mutSuf[i];
        return output;
    }
}


