class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strs[0].length(); i++){
            char tmp = strs[0].charAt(i);
            for(int j = 1; j < strs.length; j++){
                // 这两个条件不能写反了
                if(i >= strs[j].length() || tmp != strs[j].charAt(i))
                    return sb.toString();
            }
            sb.append(tmp);
        }
        return sb.toString();
    }
}
