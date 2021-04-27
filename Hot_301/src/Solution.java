import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
class Solution {
    private char[] charArray;
    private int len;
    private Set<String> validExpression = new HashSet<>(); // 去重
    public List<String> removeInvalidParentheses(String s) {
        if(s == null ) return null;

        len = s.length(); // 必须是字符串的长度
        charArray = s.toCharArray();
        // 最少应该被删除的左右括号数量
        int leftRemove = 0;
        int rightRemove = 0;
        // step 1：统计最少应该被删除的左右括号数量
        for(char ch : charArray){
            if(ch == '('){
                leftRemove++;
            } else if(ch == ')'){
                if(leftRemove != 0){
                    leftRemove--;
                } else{
                    rightRemove++;
                }
            }
        }
        // step 2: dfs，尝试每一种可能的删除操作
        StringBuffer path = new StringBuffer();
        dfs(0,0,0,leftRemove,rightRemove, path);
        return new ArrayList<>(this.validExpression);
    }
    /**
     * @param index       当前遍历到的下标
     * @param leftCount   已经遍历到的左括号的个数
     * @param rightCount  已经遍历到的右括号的个数
     * @param leftRemove  最少应该删除的左括号的个数
     * @param rightRemove 最少应该删除的右括号的个数
     * @param path        一个可能的结果
     */
    public void dfs(int index, int leftCount, int rightCount,
                    int leftRemove, int rightRemove, StringBuffer path){
        // if(leftRemove == 0 && rightRemove == 0 && index == len){
        //     validExpression.add(path.toString());
        //     return;
        // }
        // TODO: 上面的写法是不正确的，当index已经越界了，但是leftRemove和rightRemove不为0就会出现数组越界的问题
        if(index == len){
            if(leftRemove == 0 && rightRemove == 0){
                validExpression.add(path.toString());
            }
            return;
        }
        char ch = charArray[index];
        // 可能的操作 1：删除当前遍历到的字符
        if(ch == '(' && leftRemove > 0){
            // 由于 leftRemove > 0，并且当前遇到的是左括号，因此可以尝试删除当前遇到的左括号
            dfs(index + 1, leftCount, rightCount, leftRemove - 1, rightRemove, path);
        } else if(ch == ')' && rightRemove > 0){
            // 由于 rightRemove > 0，并且当前遇到的是右括号，因此可以尝试删除当前遇到的右括号
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove - 1, path);
        }
        // 可能的操作 2：保留当前遍历到的字符
        path.append(ch);
        // 如果不是括号，继续深度优先遍历
        if(ch != '(' && ch != ')'){
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove, path);
        }else if(ch == '('){
            // 考虑左括号
            dfs(index + 1, leftCount + 1, rightCount, leftRemove, rightRemove, path);
        } else if(rightCount < leftCount){
            // 考虑右括号
            dfs(index + 1, leftCount, rightCount + 1, leftRemove, rightRemove, path);
        }
        // 回溯
        path.deleteCharAt(path.length() -1);
    }
}