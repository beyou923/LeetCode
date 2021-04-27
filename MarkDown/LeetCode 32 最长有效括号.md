## 题目描述

给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。

**示例 1：**

>输入：s = "(()"
>输出：2
>解释：最长有效括号子串是 "()"

**示例 2：**

>输入：s = ")()())"
>输出：4
>解释：最长有效括号子串是 "()()"

**示例 3：**

>输入：s = ""
>输出：0

**提示：**

* 0 <= s.length <= 3 * 10^4
* s[i] 为 '(' 或 ')'

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/longest-valid-parentheses](https://leetcode-cn.com/problems/longest-valid-parentheses?fileGuid=k3Dcc3TgG3rHDxPh)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

```shell
dp[i]就记录以s[i]结尾的字符串能够构成的最长的匹配串的长度，显然如果s[i]是(，没什么好说的，以i为结尾一定不能构成合法的串，那么dp[i]=0。也就是说只有s[i]是)的时候，dp[i]的值才有可能大于0。那么这个值会是多少呢？我们继续来推算。显然，我们需要观察i-1的位置，如果i-1的位置是(，那么说明我们至少可以构成一个match。构成这个match之后呢？其实就要看dp[i-2]了。因为在一个合法的结果后面加上一个括号对显然也是合法的。所以如果i-1处的结果是(，那么我们可以得到dp[i] = dp[i-2] + 2。那如果i-1的位置也是)呢？我们来举个例子看看就知道了。
s:    a       b    (    )     )
idx:  i-4    i-3   i-2  i-1   i
```
从上面这个例子可以看出来，当i-1的位置也是)的时候，我们可以知道dp[i-1]有可能不为0，那么很简单，我们只需要跳过dp[i-1]长度的位置就好了。比如上面这个例子，i-1的位置可以和i-2构成match，那么我们就可以跳过dp[i-1]也就是2个长度，去查看i-3的位置和i是否构成match，如果构成match，那么最终的答案就是dp[i-1] + 2 + dp[i-4]。因为dp[i-4]也有可能还有合法的串。
## 代码

```java
import java.util.Arrays;
class Solution {
public int longestValidParentheses(String s) {
int result = 0;
int[] dp = new int[s.length()];
Arrays.fill(dp,0);
for(int i = 1; i < s.length(); i++){
if(s.charAt(i) == ')'){
// 如果i-1是（，那么我们判断i-2
if(s.charAt(i-1) == '('){
if(i > 1) dp[i] = dp[i -2] + 2;
else dp[i] = 2;
}
// 如果i-1也是)，我们需要继续往前判断
else if (i - dp[i -1] > 0 && s.charAt(i - dp[i - 1] - 1) == '('){
if(i - dp[i - 1] - 2 >= 0)
dp[i] = 2 + dp[i - 1] + dp[i - dp[i - 1] - 2];
else
dp[i] = 2 + dp[i - 1];
}
}
}
// 找dp中最大值
for (int num:dp) {
if (result < num)
result = num;
}
return result;
}
}
```
## 复杂度分析

### 时间复杂度：O(n)

### 空间复杂度：O(n)

