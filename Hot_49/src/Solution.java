import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length < 1) return null;

        Map<String, List<String>> word = new HashMap<>();
        for(String s : strs){
            char[] tmp = s.toCharArray();
            Arrays.sort(tmp);
            String key = new String(tmp);
            List<String> list = word.getOrDefault(key,new ArrayList<String>());
            list.add(s);
            word.put(key,list);
        }
        return new ArrayList<List<String>>(word.values());
    }
}

