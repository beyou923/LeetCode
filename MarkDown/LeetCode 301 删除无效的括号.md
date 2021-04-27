## 题目

给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。

返回所有可能的结果。答案可以按 任意顺序 返回。

**示例 1**:

>输入: "()())()"
>输出: ["()()()", "(())()"]

**示例 2**:

>输入: "(a)())()"
>输出: ["(a)()()", "(a())()"]

**示例 3**:

>输入: ")("
>输出: [""]



**提示**：

* 1 <= s.length <= 25
* s 由小写英文字母以及括号 '(' 和 ')' 组成
* s 中至多含 20 个括号

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/remove-invalid-parentheses](https://leetcode-cn.com/problems/remove-invalid-parentheses?fileGuid=VGPtCyDkHk6gDDqH)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：DFS

step 1：一次遍历计算出多余的「左括号」和「右括号」

根据括号匹配规则和根据求解[ 22. 括号生成](https://leetcode-cn.com/problems/generate-parentheses/?fileGuid=VGPtCyDkHk6gDDqH)的经验，我们知道：**如果当前遍历到的左括号的数目严格小于右括号的数目则表达式无效**（这一点非常重要）。因此，我们可以遍历一次输入字符串，统计「左括号」和「右括号」出现的次数。

* 当遍历到「右括号」的时候，
    * 如果此时「左括号」的数量不为 0，因为 「右括号」可以与之前遍历到的「左括号」匹配，此时「左括号」出现的次数 −1；
    * 如果此时「左括号」的数量为 0，「右括号」数量加 1；
* 当遍历到「左括号」的时候，「左括号」数量加 1。

通过这样的计数规则，最后「左括号」和「右括号」的数量就是各自最少应该删除的数量。

step 2： 通过具体例子设计算法:

使用示例 2 向大家展示程序的执行流程。

![图片](https://uploader.shimo.im/f/5iwRlCMGLChgi2p6.png!thumbnail?fileGuid=VGPtCyDkHk6gDDqH)

**说明**：我们设计变量 leftCount 和 rightCount 分别表示在遍历的过程中已经遍历到的左括号和右括号的数量，统计它们是为了方便 剪枝。这是因为 只有当「已经遍历到的左括号的数量」严格大于「已经遍历到的右括号的数量」的时候，才可以继续添加「右括号」。大家可以结合代码进行理解。

```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
class Solution {
    private char[] charArray;
    private int len;
    private Set<String> validExpression = new HashSet<>();// 去重
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
```
