## 题目描述

给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。

**示例1:**

```shell
输入: pattern = "abba", str = "dog cat cat dog"
输出: true
```
**示例 2:**
```shell
输入:pattern = "abba", str = "dog cat cat fish"
输出: false
```
**示例 3:**
```shell
输入: pattern = "aaaa", str = "dog cat cat dog"
输出: false
```
**示例 4:**
```shell
输入: pattern = "abba", str = "dog dog dog dog"
输出: false
```
**说明:**
* 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。

链接：[https://leetcode-cn.com/problems/word-pattern](https://leetcode-cn.com/problems/word-pattern?fileGuid=XPHtKccKhwGdHhVQ)

## 思路

把pattern映射成一个字符串，把单词组字符串str也影射成一个字符串，如果这个字符串完全相同，则模式匹配。

## 代码

```java
import java.util.HashMap;
import java.util.Map;
public class Solution {
    public boolean wordPattern(String patten, String s){
        String pattenStr = patten2Array(patten);
        String str = string2Array(s);
        return pattenStr.equals(str);
    }
    public String patten2Array(String patten){
        String[] arrayPatten = patten.trim().split("");
        return strArray2Patten(arrayPatten);
    }
    public String string2Array(String s){
        String[] arrayStr = s.trim().split(" ");
        return strArray2Patten(arrayStr);
    }
    public String strArray2Patten(String[] str){
        Map<String,Character> map = new HashMap<>();
        char i = 0;
        StringBuilder sb = new StringBuilder();
        for (String s: str) {
            if (!map.containsKey(s)){
                map.put(s,i);
                char c = (char) ('a' + i);
                sb.append(c);
                i++;
            } else {
                char index = map.get(s);
                char c = (char) ('a' + index);
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
```
## 复杂度分析

时间复杂度：O(n)

空间复杂度：O(n)

