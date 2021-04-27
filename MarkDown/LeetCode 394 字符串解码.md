## 题目

给定一个经过编码的字符串，返回它解码后的字符串。编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

**示例 1**：

>输入：s = "3[a]2[bc]"
>输出："aaabcbc"

**示例 2**：

>输入：s = "3[a2[c]]"
>输出："accaccacc"

**示例 3**：

>输入：s = "2[abc]3[cd]ef"
>输出："abcabccdcdcdef"

**示例 4**：

>输入：s = "abc3[cd]xyz"
>输出："abccdcdcdxyz"

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/decode-string](https://leetcode-cn.com/problems/decode-string?fileGuid=XcpyPkT8vRqwkxY3)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：辅助栈

本题关键在于括号内嵌套括号，需要从内向外生成与字符串拼接，这与栈的先入后出特性对应。

**算法流程**：

* 构建辅助栈 stack， 遍历字符串 s 中每个字符 c；
    * 当 c 为数字时，将数字字符转化为数字 multi，用于后续倍数计算；
    * 当 c 为字母时，在 res 尾部添加 c；
    * 当 c 为 [ 时，将当前 multi 和 res 入栈，并分别置空置 0：
        * 记录此 [ 前的临时结果 res 至栈，用于发现对应 ] 后的拼接操作；
        * 记录此 [ 前的倍数 multi 至栈，用于发现对应 ] 后，获取 multi × [...] 字符串。
        * 进入到新 [ 后，res 和 multi 重新记录。
    * 当 c 为 ] 时，stack 出栈，拼接字符串 res = last_res + cur_multi * res，其中:
        * last_res是上个 [ 到当前 [ 的字符串，例如 "3[a2[c]]" 中的 a；
        * cur_multi是当前 [ 到 ] 内字符串的重复倍数，例如 "3[a2[c]]" 中的 2。
* 返回字符串 res。

fd

```java
import java.util.LinkedList;
class Solution {
    public String decodeString(String s) {
        if(s == null) return null;
        LinkedList<String> stackRes = new LinkedList<String>();
        LinkedList<Integer> stackMulti = new LinkedList<Integer>();
        int multi = 0;
        StringBuffer res = new StringBuffer();
        // 遍历字符串s
        for(char ch : s.toCharArray()){
            // 将数字读取出来
            if(Character.isDigit(ch)){
                multi = 10 * multi + Integer.parseInt(ch + "");
            }else if(Character.isAlphabetic(ch)){
                //将 ‘[’之前的字母追加到一起
                res.append(ch);
            }else if(ch == '['){
                // 将紧靠'['字符之前的数子和有效字符串加入栈中，同时将二者清零
                stackMulti.addLast(multi);
                multi = 0;
                stackRes.addLast(res.toString());
                res = new StringBuffer();
            } else if(ch == ']'){
                // 遇到 ']'同时弹出 stackRes和 stackMulti栈顶元素
                StringBuffer tmp = new StringBuffer();
                // 紧靠 ‘[’ 字符之前的数字
                int times = stackMulti.removeLast();
                // 将紧靠 ‘[’ 字符之后的字符串重复 times次
                for(int i = 0; i < times; i++)
                    tmp.append(res.toString());
                res = new StringBuffer(stackRes.removeLast() + tmp);
            }
        }
        return res.toString();
    }
}
```
时间复杂度：O(n)
空间复杂度：O(n)

