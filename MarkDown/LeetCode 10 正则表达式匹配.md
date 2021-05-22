## 题目

给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

* '.' 匹配任意单个字符
* '*' 匹配零个或多个前面的那一个元素

所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

**示例 1**：

>输入：s = "aa" p = "a"
>输出：false
>解释："a" 无法匹配 "aa" 整个字符串。

**示例 2:**

>输入：s = "aa" p = "a*"
>输出：true
>解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。

**示例 3**：

>输入：s = "ab" p = ".*"
>输出：true
>解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。

**示例 4**：

>输入：s = "aab" p = "c*a*b"
>输出：true
>解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。

**示例 5**：

>输入：s = "mississippi" p = "mis*is*p*."
>输出：false

**提示**：

* 0 <= s.length <= 20
* 0 <= p.length <= 30
* s 可能为空，且只包含从 a-z 的小写字母。
* p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
* 保证每次出现字符 * 时，前面都匹配到有效的字符

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/regular-expression-matching](https://leetcode-cn.com/problems/regular-expression-matching?fileGuid=XHyYpDKpQvJqKCVv)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 方法一：回溯法

回溯法，也就是把所有可能情况都走一遍，只药有一种情况满足即可。具体而言，通过不停地剪去s和p相同的首部，直到某一个或两个都被剪空为止。

* 首先我们从没有`*`的最简单情况来看，这是只需要扫描一遍s和p，从首部开始比较对应元素是否相等即可，如果相同就可以剪掉，比较下一个，即
```plain
s[i] == p[i] || p[i] == '.'
```
* 考虑 p中有`*`的情况，`*`前元素可以出现0次或者多次，那么注意在检测到p中第i个元素的下一个元素为`*`时，需要分2种情况讨论：
    * p的第i个元素在s中出现0次：此时保持s不变，将p剪掉2个元素，继续递归
    * p的第i个元素在s中出现一次或多次：此时，比较p中第i个元素与s的首元素，如果相同，剪掉s的首元素，保持p不变继续递归

只要上述两种情况中有一种匹配上就可以了

```java
class Solution {
    public boolean isMatch(String s, String p) {
        if(p.isEmpty()) return s.isEmpty();
        // 比较第一个字符是否相等
        boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        // 如果下一个字符是 *
        if (p.charAt(1) == '*'){
            // TODO: isMatch(s,p.substring(2))表示p中第0个字符没有出现在s中
            // TODO: firstMatch && isMatch(s.substring(1),p) 表示p中第0个字符在s中出现了1次或多次
            return isMatch(s,p.substring(2)) || (firstMatch && isMatch(s.substring(1),p));
        }else {
            // 一般情况
            return firstMatch && isMatch(s.substring(1),p.substring(1));
        }
    }
}
```
### 方法二：动态规划

```java
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        // 初始化dp数组
        dp[0][0] = true;
        for(int j = 2; j <= n; j++)
            dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(p.charAt(j) == '*'){
                    // p中第j个位置为 * 需要考虑两种情况
                    // 情况1：p中 * 前面的字符没有在s中出现 --> 保持s不变，剪掉p中前2个字符 dp[i + 1][j - 1]
                    // 情况2：p中 * 前面的字符与在s中出现了 --> 保持p不变，剪掉s中前一个字符 firstMatch(s,p,i,j - 1) && dp[i][j + 1]
                    dp[i + 1][j + 1] = dp[i + 1][j - 1] || firstMatch(s,p,i,j - 1) && dp[i][j + 1];
                } else{
                    // 一般情况
                    dp[i + 1][j + 1] = firstMatch(s,p,i,j) && dp[i][j];
                }
            }
        }
        return dp[m][n];
    }
    // 比较s和p中两个指定位置的字符是否匹配
    public boolean firstMatch(String s, String p, int i , int j){
        return s.charAt(i) == p.charAt(j) || p.charAt(j) == '.';
    }
}
```
时间复杂度：O(mn)
空间复杂度：O(mn)

其中，m和n分别是s和p的长度

