import java.util.HashMap;
import java.util.Map;

public class Solution {
    public boolean wordPattern(String patten, String s){
        String pattenStr = patten2Array(patten);
        String str = string2Array(s);
        return pattenStr.equals(str);
    }

    public String patten2Array(String patten){
        String[] arrayPatten = patten.trim().split("");
        return strArray2Patten(arrayPatten);
    }
    public String string2Array(String s){
        String[] arrayStr = s.trim().split(" ");
        return strArray2Patten(arrayStr);
    }
    public String strArray2Patten(String[] str){
        Map<String,Character> map = new HashMap<>();
        char i = 0;
        StringBuilder sb = new StringBuilder();
        for (String s: str) {
            if (!map.containsKey(s)){
                map.put(s,i);
                char c = (char) ('a' + i);
                sb.append(c);
                i++;
            } else {
                char index = map.get(s);
                char c = (char) ('a' + index);
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
