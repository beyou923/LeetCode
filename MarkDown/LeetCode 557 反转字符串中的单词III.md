## 题目

给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

**示例**：

>输入："Let's take LeetCode contest"
>输出："s'teL ekat edoCteeL tsetnoc"

**提示**：

* 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/reverse-words-in-a-string-iii](https://leetcode-cn.com/problems/reverse-words-in-a-string-iii?fileGuid=Y8XWkh9gt86kyHgk)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

找到字符串s中的每个单词并将其反转

```java
class Solution {
    public String reverseWords(String s) {
        int length = s.length();
        int i = 0;
        StringBuffer sb = new StringBuffer();
        while (i < length){
            // 找单词
            int start = i;
            while (i < length && s.charAt(i) != ' '){
                i++;
            }
            // 将单词反转
            for (int index = i - 1; index >= start; index--){
                sb.append(s.charAt(index));
            }
            // 添加单词间的空格
            while (i < length && s.charAt(i) == ' '){
                sb.append(' ');
                i++;
            }
        }
        return sb.toString();
    }
}
```
**时间复杂度**：O(n)
**空间复杂度**：O(n)

