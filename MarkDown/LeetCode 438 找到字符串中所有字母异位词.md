## 题目

给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

说明：

* 字母异位词指字母相同，但排列不同的字符串。
* 不考虑答案输出的顺序。

**示例 1**:

>输入:
>s: "cbaebabacd" p: "abc"
>输出:
>[0, 6]
>解释:
>起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
>起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。

**示例 2**:

>输入:
>s: "abab" p: "ab"
>输出:
>[0, 1, 2]
>解释:
>起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
>起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
>起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/find-all-anagrams-in-a-string](https://leetcode-cn.com/problems/find-all-anagrams-in-a-string?fileGuid=3QYXvRKRgt3pYXVt)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：滑动窗口

本题的思想和[LeetCode 209](https://leetcode-cn.com/problems/minimum-size-subarray-sum?fileGuid=3QYXvRKRgt3pYXVt)和[LeetCode 242](https://leetcode-cn.com/problems/valid-anagram/?fileGuid=3QYXvRKRgt3pYXVt)和[LeetCode 567](https://leetcode-cn.com/problems/permutation-in-string/?fileGuid=3QYXvRKRgt3pYXVt)思路一样，都是用滑动窗口法。维护一个大小为p.length()大小的窗口，比较窗口内的字符串与p是否为异位词即可。具体而言，创建两个数组needs和window，前者存储p中各个字符出现的次数，后者保存窗口内每个字符出现的次数，如果窗口满后，两个数组相同，则窗口内的字符串和p是异位词，将窗口的起始位置加入list，否则不是异位词

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s == null || p.length() > s.length()) return res;
        char[] needs = new char[26];
        char[] window = new char[26];
        // 统计p中每个字符出现的次数  needs相当于一个map
        for(char c : p.toCharArray()){
            needs[c - 'a']++;
        }
        //start和end分别控制窗口的前端和后端
        int start = 0, end = 0;
        while(end < s.length()){
            char ch = s.charAt(end);
            window[ch - 'a']++; // 向窗口加入数据
            //维护一个长度为p.length()的窗口，并更新答案
            if(end - start + 1 == p.length()){
                if(Arrays.equals(window, needs))
                    res.add(start);
                // 将滑动窗口最左边元素移出窗口
                window[s.charAt(start) - 'a']--;
                // 滑动窗口向右移动
                start++;
            }
            end++;
        }
        return res;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(1)

其中，n为字符串s的长度

