import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        int len = digits.length();
        if(len < 1) return res;
        Map<Character,String> map = new HashMap<Character,String>(){
            {
                put('2',"abc");
                put('3',"def");
                put('4', "ghi");
                put('5', "jkl");
                put('6', "mno");
                put('7',"pqrs");
                put('8',"tuv");
                put('9',"wxyz");

            }
        }; //注意这种初始化方法
        dfs(res,digits,0,map,new StringBuilder());
        return res;
    }

    public void dfs(List<String> res, String digits, int index,Map<Character,String> map,StringBuilder str){
        if(index == digits.length()){
            res.add(str.toString());
            return;
        }
        char digit = digits.charAt(index);
        String letters = map.get(digit);
        for(int i = 0; i < letters.length(); i++){
            str.append(letters.charAt(i));
            dfs(res,digits,index + 1, map,str);
            str.deleteCharAt(index); // 回溯
        }
    }
}