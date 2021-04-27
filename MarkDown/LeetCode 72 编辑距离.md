## 题目

给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。你可以对一个单词进行如下三种操作：

* 插入一个字符
* 删除一个字符
* 替换一个字符

**示例 1**：

>输入：word1 = "horse", word2 = "ros"
>输出：3
>解释：
>horse -> rorse (将 'h' 替换为 'r')
>rorse -> rose (删除 'r')
>rose -> ros (删除 'e')

**示例 2**：

>输入：word1 = "intention", word2 = "execution"
>输出：5
>解释：
>intention -> inention (删除 't')
>inention -> enention (将 'i' 替换为 'e')
>enention -> exention (将 'n' 替换为 'x')
>exention -> exection (将 'n' 替换为 'c')
>exection -> execution (插入 'u')

**提示**：

* 0 <= word1.length, word2.length <= 500
* word1 和 word2 由小写英文字母组成

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/edit-distance](https://leetcode-cn.com/problems/edit-distance?fileGuid=cRQDVt9qhrkxDkwq)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：动态规划

* 确定dp数组以及下标的含义

dp[i][j] 表示以下标i-1为结尾的字符串word1，和以下标j-1为结尾的字符串word2，最近编辑距离为dp[i][j]。

* 确定递推公式

在确定递推公式的时候，首先要考虑清楚编辑的几种操作，整理如下：

if (word1[i - 1] == word2[j - 1])

不操作

if (word1[i - 1] != word2[j - 1])

增

删

换

也就是如上四种情况。

if (word1[i - 1] == word2[j - 1]) 那么说明不用任何编辑，dp[i][j] 就应该是 dp[i - 1][j - 1]，即dp[i][j] = dp[i - 1][j - 1];

if (word1[i - 1] != word2[j - 1])，此时就需要编辑了，如何编辑呢？

    * 操作一：word1增加一个元素，使其word1[i - 1]与word2[j - 1]相同，那么就是以下标i-2为结尾的word1 与 j-1为结尾的word2的最近编辑距离 加上一个增加元素的操作。即 dp[i][j] = dp[i - 1][j] + 1;
    * 操作二：word2添加一个元素，使其word1[i - 1]与word2[j - 1]相同，那么就是以下标i-1为结尾的word1 与 j-2为结尾的word2的最近编辑距离 加上一个增加元素的操作。即 dp[i][j] = dp[i][j - 1] + 1;**word2添加一个元素，相当于word1删除一个元素，也就是相当于word1删除一个元素，操作数是一样**！可以这样理解：给定了两个单词，设为 A 和 B，对单词 A 删除一个字符和对单词 B 插入一个字符是等价的。例如当单词 A 为 doge，单词 B 为 dog 时，我们既可以删除单词 A 的最后一个字符 e，得到相同的 dog（可理解为是A迁就B），也可以在单词 B 末尾添加一个字符 e，得到相同的 doge（B迁就A）
    * 操作三：替换元素，word1替换word1[i - 1]，使其与word2[j - 1]相同，此时不用增加元素，那么以下标i-2为结尾的word1 与 j-2为结尾的word2的最近编辑距离 加上一个替换元素的操作。即 dp[i][j] = dp[i - 1][j - 1] + 1;

综上，当 if (word1[i - 1] != word2[j - 1]) 时取最小的，即：dp[i][j] = min({dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]}) + 1;

* dp数组如何初始化

回顾一下dp[i][j]的定义。dp[i][j] 表示以下标i-1为结尾的字符串word1，和以下标j-1为结尾的字符串word2，最近编辑距离为dp[i][j]。那么dp[i][0] 和 dp[0][j] 表示什么呢？dp[i][0] ：以下标i-1为结尾的字符串word1，和空字符串word2，最近编辑距离为dp[i][0]。那么dp[i][0]就应该是i，对空字符串做添加元素的操作就可以了，即：dp[i][0] = i;同理dp[0][j] = j;

* 确定遍历顺序

从左到右从上到下

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        if(len1 < 1 || len2 < 1) return len1 < 1 ? len2 : len1;
        int[][] dp = new int[len1 + 1][len2 + 1];
        //初始化第一行
        for(int i = 0; i <= len1; i++)  dp[i][0] = i ;
        //初始化第一列
        for(int j = 0; j <= len2; j++)  dp[0][j] = j ;
        // 开始 dp
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                } else{
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}
```
时间复杂度：O(mn)
空间复杂度：O(mn)

m和n分别是word1和word2的长度

