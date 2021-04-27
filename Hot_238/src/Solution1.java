class Solution1 {
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] output = new int[length];

        // output[i] 表示索引 i 左侧所有元素的乘积
        // 因为索引为 '0' 的元素左侧没有元素， 所以 output[0] = 1
        for (int i = 0; i < length; i++) {
            if(i == 0) {
                output[i] = 1;
            } else {
                output[i] = nums[i - 1] * output[i - 1];
            }
        }

        // mutSuf 为右侧所有元素的乘积
        // 刚开始右边没有元素，所以 mutSuf = 1
        int mutSuf  = 1;
        for (int i = length - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 output[i]，右边的乘积为 mutSuf
            output[i] = output[i] * mutSuf ;
            // mutSuf 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 mutSuf 上
            mutSuf  *= nums[i];
        }
        return output;
    }
}
