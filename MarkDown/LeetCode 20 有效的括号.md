## 题目描述

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。有效字符串需满足：

1. 左括号必须用相同类型的右括号闭合。
2. 左括号必须以正确的顺序闭合。

**示例 1：**

```shell
输入：s = "()"
输出：true
```
**示例 2：**
```shell
输入：s = "()[]{}"
输出：true
```
**示例 3：**
```shell
输入：s = "(]"
输出：false
```
**示例 4：**
```shell
输入：s = "([)]"
输出：false
```
**示例 5：**
```shell
输入：s = "{[]}"
输出：true
```
**提示：**
* 1 <= s.length <= 104
* s 仅由括号 '()[]{}' 组成

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/valid-parentheses](https://leetcode-cn.com/problems/valid-parentheses)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

遍历字符串s，遇到左括号就入栈，遇到有括号时候，将栈顶元素弹出，如果栈顶元素和当前元素匹配，则继续，如果不匹配则提前返回false，最后如果栈为空则返回true，否则返回false。可以定义一个map存储3种括号之间的映射关系，就可以在判断是否匹配的时候少写if-else语句了。

## 代码

```java
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
```
## 复杂度分析

### 时间复杂度：O(n)

### 空间复杂度：O(n)

