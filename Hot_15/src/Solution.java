import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if(len < 3)  // 数组长度小于3肯定没有答案
            return res;
        Arrays.sort(nums); // 数组排序（升序）
        for(int i = 0; i < len; i++){
            if(nums[i] > 0) // 当前元素大于0，说明后面的元素也大于0，不可能有答案 剪枝
                break;
            if(i > 0 && nums[i -1] == nums[i]) // 防止重复  剪枝
                continue;
            int left = i + 1, right = len - 1;
            while(left < right){
                int leftValue = nums[left], rightValue = nums[right];
                int sum = nums[i] + leftValue + rightValue;
                if(sum == 0){
                    res.add(Arrays.asList(nums[i],leftValue,rightValue)); // 注意这个方法
                    while(left < right && nums[left] == leftValue) // 跳过重复元素
                        left++;
                    while(left < right && nums[right] == rightValue) // 跳过重复元素
                        right--;
                } else if(sum < 0) // 左指针移动
                    left++;
                else
                    right--;  // 右指针移动
            }
        }
        return res;
    }
}
