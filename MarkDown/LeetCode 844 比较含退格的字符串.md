## 题目

给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。

注意：如果对空文本输入退格字符，文本继续为空。

**示例 1**：

>输入：S = "ab#c", T = "ad#c"
>输出：true
>解释：S 和 T 都会变成 “ac”。

**示例 2**：

>输入：S = "ab##", T = "c#d#"
>输出：true
>解释：S 和 T 都会变成 “”。

**示例 3**：

>输入：S = "a##c", T = "#a#c"
>输出：true
>解释：S 和 T 都会变成 “c”。

**示例 4**：

>输入：S = "a#c", T = "b"
>输出：false
>解释：S 会变成 “c”，但 T 仍然是 “b”。

**提示**：

* 1 <= S.length <= 200
* 1 <= T.length <= 200
* S 和 T 只含有小写字母以及字符 '#'。

**进阶**：你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/backspace-string-compare](https://leetcode-cn.com/problems/backspace-string-compare?fileGuid=HvW6cPhYkYtQcx9C)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：双指针法

同时从后向前遍历S和T（i初始为S末尾，j初始为T末尾），记录#的数量，模拟消除的操作，如果#用完了，就开始比较S[i]和S[j]。如果S[i]和S[j]不相同返回false，如果有一个指针（i或者j）先走到的字符串头部位置，也返回false。

```java
class Solution {
    public boolean backspaceCompare(String s, String t) {
        // 记录 s 和 t 中 # 的数量
        int skipS = 0, skipT = 0;
        int i = s.length() - 1; 
        int j = t.length() - 1;
        while(true){
            // 从后向前消除 s 中的 #
            while(i >= 0) {
                if(s.charAt(i) == '#') {
                    skipS++;
                } else {
                    if(skipS > 0) {
                        skipS--;
                    } else {
                        break;
                    }
                }
                i--;
            }
            // 从后向前 消除 t 中的 #
            while (j >= 0) {
                if(t.charAt(j) == '#') {
                    skipT++;
                } else {
                    if(skipT > 0) {
                        skipT--;
                    } else {
                        break;
                    }
                }
                j--;
            }
            // 后半部分 # 消除完了， 接下来比较 s[i] != t[j]
            if(i < 0 || j < 0) break; // s 或者 t遍历到头了
            if(s.charAt(i) != t.charAt(j)) return false; 
            i--;
            j--;
        }
        // 说明 s 和 t 同时遍历完毕
        if(i == -1 && j == -1) return true;
        return false;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(1)

