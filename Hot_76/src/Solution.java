import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<Character, Integer> pattern = new HashMap<>();
    private Map<Character, Integer> content = new HashMap<>();
    public String minWindow(String s, String t) {

        for(int i = 0; i < t.length(); i++){
            char ch = t.charAt(i);
            pattern.put(ch,pattern.getOrDefault(ch,0) + 1);
        }
        int lenS = s.length();
        int subLen = Integer.MAX_VALUE;// 最小覆盖子串的长度
        int ansL = -1, ansR = -1; // 最小覆盖子串的左右边界 [ansL, ansR)
        int start = 0, end = 0; // 滑动窗口的左右边界 [start, end]
        while(end < lenS){
            char c = s.charAt(end);
            if (pattern.containsKey(c)){
                content.put(c, content.getOrDefault(c,0) + 1);
            }
            // 如果滑动窗口中包含了t中全部字符，移动窗口
            while (check() && start <= end){
                // 更新最小覆盖子串的左右边界及长度
                if (subLen > (end - start + 1)){
                    subLen = end - start + 1;
                    ansL = start;
                    ansR = end + 1;
                }
                // 移除窗口的字符如果在t中，则在窗口中次数减一
                char first = s.charAt(start);
                if (content.containsKey(first)){
                    content.put(first, content.getOrDefault(first,0) - 1);
                }
                // 收缩窗口
                start++;
            }
            // 扩展窗口
            end++;
        }
        return ansL == -1 ? "" : s.substring(ansL,ansR);
    }
    // 判断滑动窗口中是否包含t中所有字符
    // true：包含所有字符  false：不包含/不完全包含
    public boolean check(){
        for (Map.Entry<Character, Integer> entry : pattern.entrySet()){
            char key = entry.getKey();
            int times = entry.getValue();
            if (content.getOrDefault(key,0) < times){
                return false;
            }else {
                continue;
            }
        }
        return true;
    }
}
