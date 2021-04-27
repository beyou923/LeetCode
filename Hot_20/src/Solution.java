import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Solution {
    public boolean isValid(String s) {
        int len = s.length();
        if(len < 2) return false;
        Map<Character,Character> map = new HashMap<Character,Character>(){
            {
                put('(',')');
                put('[',']');
                put('{','}');
            }
        };
        LinkedList<Character> stack = new LinkedList<Character>();
        for(int i = 0; i < len; i++){
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
                stack.add(s.charAt(i));
            else if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}'){
                if (stack.isEmpty()) return false;
                else {
                    Character top = stack.removeLast();
                    if (s.charAt(i) != map.get(top)) return false;
                    else continue;
                }
            }
        }
        if(stack.isEmpty()) return true;
        else return false;
    }
}