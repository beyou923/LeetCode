import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        for(int num : nums){
            int index = Math.abs(num) -1;
            if(nums[index] < 0) // 再次出现nums[index] < 0 说明 index + 1 出现了2次
                result.add(index + 1);
            nums[index] = -nums[index]; // 将 nums[nums[i]]取反
        }
        return result;
    }
}