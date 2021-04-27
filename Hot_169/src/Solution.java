class Solution {
    // 与剑指offer 39题相同
    public int majorityElement(int[] nums) {
        // 摩尔投票法 major就是出现次数大于 n/2的数;count记录major出现的次数
        int major = 0, count = 0;
        for(int num : nums){
            if(count == 0) // major次数减为0,将当前元素设为major
                major = num;
            count += major == num ? 1 : -1; // major与当前元素相等次数加1,否则次数减1
        }
        return major;
    }
}
