class Solution {
    public String reverseWords(String s) {
        int length = s.length();
        int i = 0;
        StringBuffer sb = new StringBuffer();
        while (i < length){
            // 找单词
            int start = i;
            while (i < length && s.charAt(i) != ' '){
                i++;
            }
            // 将单词反转
            for (int index = i - 1; index >= start; index--){
                sb.append(s.charAt(index));
            }
            // 添加单词间的空格
            while (i < length && s.charAt(i) == ' '){
                sb.append(' ');
                i++;
            }
        }
        return sb.toString();
    }
}
