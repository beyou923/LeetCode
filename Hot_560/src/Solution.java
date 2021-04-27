import java.util.HashMap;
import java.util.Map;

public class Solution {
    // 枚举法
    int count = 0;
    public int subarraySum(int[] nums, int k) {
        for(int start = 0; start < nums.length; start++){
            int sum = 0;
            sum += nums[start];
            if(sum == k) count++;
            for(int i = start + 1; i < nums.length; i++){
                sum += nums[i];
                if(sum == k) count++;
            }
        }
        return count;
    }

    public int subarraySum1(int[] nums, int k) {
            int pre = 0 , count = 0;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0,1);
            for(int i = 0; i < nums.length; i++){
                pre += nums[i]; // 计算以i结尾的前缀和
                if(map.containsKey(pre - k)){
                    count += map.get(pre - k);
                }

                map.put(pre , map.getOrDefault(pre, 0) + 1);
            }
            return count;
    }
}


