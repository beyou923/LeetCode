import java.util.HashMap;
import java.util.Map;

class Solution {
    public int totalFruit(int[] tree) {
        int len = tree.length;
        if(len == 1) return 1;
        int left = 0, right = 0;
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while(right < len) {
            // 将尽可能多的元素加入窗口
            while (right < len && map.size() == 2 && map.containsKey(tree[right])){
                map.put(tree[right], map.getOrDefault(tree[right], 0) + 1);
                right++;
            }
            // 窗口已不能再添加元素，统计窗口大小，每次将最左端元素滑出窗口
            while(map.size() == 2){
                int outofWindow = tree[left];
                if(map.get(outofWindow) != 0) {
                    maxLen = Math.max(maxLen, right - left);
                    map.put(outofWindow, map.get(outofWindow) - 1);
                    left++;
                    if(map.get(outofWindow) == 0) {
                        map.remove(outofWindow);
                    }
                }
            }
            if(right == len) {
                break;
            }
            // 向滑动窗口中加入元素
            map.put(tree[right], map.getOrDefault(tree[right], 0) + 1);
            right++;
        }
        return Math.max(maxLen, right - left);
    }
}
