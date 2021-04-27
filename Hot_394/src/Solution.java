import java.util.LinkedList;
class Solution {
    public String decodeString(String s) {
        if(s == null) return null;
        LinkedList<String> stackRes = new LinkedList<String>();
        LinkedList<Integer> stackMulti = new LinkedList<Integer>();
        int multi = 0;
        StringBuffer res = new StringBuffer();
        // 遍历字符串s
        for(char ch : s.toCharArray()){
            // 将数字读取出来
            if(Character.isDigit(ch)){
                multi = 10 * multi + Integer.parseInt(ch + "");
            }else if(Character.isAlphabetic(ch)){
                //将 ‘[’之前的字母追加到一起
                res.append(ch);
            }else if(ch == '['){
                // 将紧靠'['字符之前的数子和有效字符串加入栈中，同时将二者清零
                stackMulti.addLast(multi);
                multi = 0;
                stackRes.addLast(res.toString());
                res = new StringBuffer();
            } else if(ch == ']'){
                // 遇到 ']'同时弹出 stackRes和 stackMulti栈顶元素
                StringBuffer tmp = new StringBuffer();
                // 紧靠 ‘[’ 字符之前的数字
                int times = stackMulti.removeLast();
                // 将紧靠 ‘[’ 字符之后的字符串重复 times次
                for(int i = 0; i < times; i++)
                    tmp.append(res.toString());
                res = new StringBuffer(stackRes.removeLast() + tmp);
            }
        }
        return res.toString();
    }
}