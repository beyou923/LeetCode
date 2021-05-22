// 双指针法
class Solution1 {
    public int trap(int[] height) {
        if(height.length < 1) return 0;
        int ans = 0;
        // 从左到右遍历每一列
        // 第一个柱子和最后一个柱子不接雨水
        for(int i = 1; i < height.length - 1; i++) {
            int leftHigh = height[i];
            int rightHigh = height[i];
            // 找左边最高点
            for(int j = i -1; j >= 0; j--){
                leftHigh = Math.max(height[j], leftHigh);
            }
            // 找右边最高点
            for(int j = i + 1; j < height.length; j++){
                rightHigh = Math.max(rightHigh, height[j]);
            }
            // 计算当前列的能装多少水
            ans += Math.min(leftHigh, rightHigh) - height[i];
        }
        return ans;
    }
}
// 动态规划
class Solution2 {
    public int trap(int[] height) {
        if(height.length <= 2) return 0;
        int len = height.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        int ans = 0;
        // 从左到右计算每一列左边最大高度
        for(int i = 0; i < len; i++){
            if(i == 0){
                leftMax[i] = height[i];
            }else{
                leftMax[i] = Math.max(height[i], leftMax[i -1]);
            }
        }
        // 从右到左计算每一列右边的最大高度
        for(int i = len - 1; i >= 0; i--){
            if(i == len -1 ){
                rightMax[i] = height[i];
            }else{
                rightMax[i] = Math.max(height[i], rightMax[i + 1]);
            }
        }
        // 累加每一列能装的水量
        for(int i = 1; i < len -1; i++){
            ans += Math.min(rightMax[i], leftMax[i]) - height[i];
        }
        return ans;
    }
}