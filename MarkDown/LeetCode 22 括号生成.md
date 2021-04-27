## 题目描述

数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。

**示例 1：**

```shell
输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]
```
**示例 2：**
```shell
输入：n = 1
输出：["()"]
```
**提示：**
* 1 <= n <= 8

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/generate-parentheses](https://leetcode-cn.com/problems/generate-parentheses?fileGuid=DH9R3WxvV8WDtQ8v)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：回溯法

两个变量 left 和 right 分别表示已经存在的左右括号的个数。如果 left 和 right 都为n，则说明此时生成的字符串已有n个左括号和n个右括号，且字符串合法，则存入结果中后返回；如果left 小于n，则添加左括号；如果右括号个数小于左括号个数，则添加右括号。树形图参考[这里](https://leetcode-cn.com/problems/generate-parentheses/solution/ru-men-ji-bie-de-hui-su-fa-xue-hui-tao-lu-miao-don/?fileGuid=DH9R3WxvV8WDtQ8v)

![图片](https://uploader.shimo.im/f/kNcxFABQZhLYRcMr.png!thumbnail?fileGuid=DH9R3WxvV8WDtQ8v)


## 代码

```java
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
```
## 
