## 题目

给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

**示例 1**：

>输入："abc"
>输出：3
>解释：三个回文子串: "a", "b", "c"

**示例 2**：

>输入："aaa"
>输出：6
>解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"

**提示**：

* 输入的字符串长度不会超过 1000 。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/palindromic-substrings](https://leetcode-cn.com/problems/palindromic-substrings?fileGuid=YHcWVDV38rrwHttY)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 方法一：暴力解法

3层for循环，外面两层for循环确定子串的起始位置start和结束位置end，最内层循环判断在区间[start,end]内的子串是否是回文串

```java
class Solution {
    public int countSubstrings(String s) {
        if(s == null || s.length() < 1) return 0;
        int count = 0;
        for(int start = 0; start < s.length(); start++){
            for(int end = s.length() - 1; end >= start; end--){
                boolean flag = true;
                for(int i = start, j = end; i <= j; i++, j--){
                    if(s.charAt(i) == s.charAt(j)) {
                        continue;
                    } else{
                        flag = false;
                    }
                }
                if(flag == true) count++;
            }
        }
        return count;
    }
}
```
时间复杂度：O(n^3)
空间复杂度：O(1)

### 方法二：动态规划

1. 确定dp数组以及下标含义

dp[i][j]表示区间范围[i,j]的子串是否为回文串，如果是回文串则dp[i][j] = true，否则,dp[i][j] = false

2. 确定递推公式

在确定递推公式时，就要分析如下几种情况。整体上是两种，就是s[i]与s[j]相等，s[i]与s[j]不相等这两种。

* 当s[i]与s[j]不相等，那没啥好说的了，dp[i][j]一定是false。
* 当s[i]与s[j]相等时，这就复杂一些了，有如下三种情况
    * 情况一：下标i 与 j相同，同一个字符例如a，当然是回文子串
    * 情况二：下标i 与 j相差为1，例如aa，也是文子串
    * 情况三：下标：i 与 j相差大于1的时候，例如cabac，此时s[i]与s[j]已经相同了，我们看i到j区间是不是回文子串就看aba是不是回文就可以了，那么aba的区间就是 i+1 与 j-1区间，这个区间是不是回文就看dp[i + 1][j - 1]是否为true。
3. 初始化dp数组

初始时候dp数组全为false

4. 确定遍历顺序

由于dp[i][j]要依赖于dp[i+1][j-1]，dp[i+1][j-1]在dp[i][j]的左下角，所以需要从下到上，从左到右遍历，由于i <= j，所以第二层循环需要从j = i开始循环

```java
class Solution {
    public int countSubstrings(String s) {
        if(s == null || s.length() < 1) return 0;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int count = 0;
        for(int i = len -1; i >= 0 ;i--){
            // 根据dp[i][j]的定义 i <= j 所以循环从j=i开始
            for(int j = i; j < len;j++){
                if(s.charAt(i) == s.charAt(j)){
                    if(j - i <= 1){
                        dp[i][j] = true;
                        count++;
                    } else if(dp[i + 1][j - 1]){
                        dp[i][j] = true;
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
```
时间复杂度：O(n^2)
空间复杂度：O(n^2)

其中，n是字符串s的长度

