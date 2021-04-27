import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        dfs(0, 0, n, "", result);
        return result;
    }
    public static void dfs(int left, int right, int n, String str, List<String> result){
        if(left == n && right == n){
            result.add(str);
            return;
        }
        if(left < n){
            dfs(left + 1, right, n, str + "(", result);
        }
        // 右括号不够
        if(right < left){
            dfs(left, right + 1, n, str + ")", result);
        }
    }
    public static void main(String[] args){
        int n = 3;
        List<String> res = new ArrayList<>();
        res = generateParenthesis(n);
    }
}