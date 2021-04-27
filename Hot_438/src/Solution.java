import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s == null || p.length() > s.length()) return res;
        char[] needs = new char[26];
        char[] window = new char[26];
        // 统计p中每个字符出现的次数  needs相当于一个map
        for(char c : p.toCharArray()){
            needs[c - 'a']++;
        }
        //start和end分别控制窗口的前端和后端
        int start = 0, end = 0;
        while(end < s.length()){
            char ch = s.charAt(end);
            window[ch - 'a']++; // 向窗口加入数据
            //维护一个长度为p.length()的窗口，并更新答案
            if(end - start + 1 == p.length()){
                if(Arrays.equals(window, needs))
                    res.add(start);
                // 将滑动窗口最左边元素移出窗口
                window[s.charAt(start) - 'a']--;
                // 滑动窗口向右移动
                start++;
            }
            end++;
        }
        return res;
    }
}
