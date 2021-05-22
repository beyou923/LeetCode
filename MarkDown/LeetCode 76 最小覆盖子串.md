## 题目

给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。

**示例 1**：

>输入：s = "ADOBECODEBANC", t = "ABC"
>输出："BANC"

**示例 2**：

>输入：s = "a", t = "a"
>输出："a"

**提示**：

* 1 <= s.length, t.length <= 10^5
* s 和 t 由英文字母组成

**进阶**：你能设计一个在 o(n) 时间内解决此问题的算法吗？

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/minimum-window-substring](https://leetcode-cn.com/problems/minimum-window-substring?fileGuid=qTxpvY6HyQHrWP3k)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：哈希表 + 滑动窗口

本问题要求我们返回字符串 s 中包含字符串 t 的全部字符的最小窗口。我们称包含 t 的全部字母的窗口为「可行」窗口。

我们可以用滑动窗口的思想解决这个问题。在滑动窗口类型的问题中都会有两个指针，一个用于「延伸」现有窗口的 r 指针，和一个用于「收缩」窗口的 ll 指针。在任意时刻，只有一个指针运动，而另一个保持静止。我们在 s 上滑动窗口，通过移动 r 指针不断扩张窗口。当窗口包含 t 全部所需的字符后，如果能收缩，我们就收缩窗口直到得到最小窗口。

如何判断当前的窗口包含所有 t 所需的字符呢？我们可以用一个哈希表表示 t 中所有的字符以及它们的个数，用一个哈希表动态维护窗口中所有的字符以及它们的个数，如果这个动态表中包含 t 的哈希表中的所有字符，并且对应的个数都不小于 t 的哈希表中各个字符的个数，那么当前的窗口是「可行」的。

```java
import java.util.HashMap;
import java.util.Map;
class Solution {
    private Map<Character, Integer> pattern = new HashMap<>();
    private Map<Character, Integer> content = new HashMap<>();
    public String minWindow(String s, String t) {
        for(int i = 0; i < t.length(); i++){
            char ch = t.charAt(i);
            pattern.put(ch,pattern.getOrDefault(ch,0) + 1);
        }
        int lenS = s.length();
        int subLen = Integer.MAX_VALUE;// 最小覆盖子串的长度
        int ansL = -1, ansR = -1; // 最小覆盖子串的左右边界 [ansL, ansR)
        int start = 0, end = 0; // 滑动窗口的左右边界 [start, end]
        while(end < lenS){
            char c = s.charAt(end);
            if (pattern.containsKey(c)){
                content.put(c, content.getOrDefault(c,0) + 1);
            }
            // 如果滑动窗口中包含了t中全部字符，移动窗口
            while (check() && start <= end){
                // 更新最小覆盖子串的左右边界及长度
                if (subLen > (end - start + 1)){
                    subLen = end - start + 1;
                    ansL = start;
                    ansR = end + 1;
                }
                // 移除窗口的字符如果在t中，则在窗口中次数减一
                char first = s.charAt(start);
                if (content.containsKey(first)){
                    content.put(first, content.getOrDefault(first,0) - 1);
                }
                // 收缩窗口
                start++;
            }
            // 扩展窗口
            end++;
        }
        return ansL == -1 ? "" : s.substring(ansL,ansR);
    }
    // 判断滑动窗口中是否包含t中所有字符
    // true：包含所有字符  false：不包含/不完全包含
    public boolean check(){
        for (Map.Entry<Character, Integer> entry : pattern.entrySet()){
            char key = entry.getKey();
            int times = entry.getValue();
            if (content.getOrDefault(key,0) < times){
                return false;
            }else {
                continue;
            }
        }
        return true;
    }
}
```
时间复杂度：O(c*s + t)
空间复杂度：O(c)

其中，c为字符集的大小

