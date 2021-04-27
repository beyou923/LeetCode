public class Solution {
    public int maxProduct(int[] nums) {
        int[] f = new int[nums.length];
        int[] g = new int[nums.length];
        int result = nums[0];
        f[0] = nums[0];
        g[0] = nums[0];

        for(int i = 1; i < nums.length; i++){
            f[i] = Integer.max(Integer.max(f[i-1] * nums[i], g[i-1] * nums[i]), nums[i]);
            g[i] = Integer.min(Integer.min(f[i-1] * nums[i], g[i-1] * nums[i]), nums[i]);

            if(f[i] > result)
                result = f[i];
        }
        return result;
    }
}
