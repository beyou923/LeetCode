import java.util.HashMap;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        for (int e : nums) {
            if (hashMap.containsKey(e))
                return true;
            else
                hashMap.put(e,e);
        }
        return false;
    }
}