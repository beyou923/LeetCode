## 题目描述

编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。

**示例 1：**

>输入：strs = ["flower","flow","flight"]
>输出："fl"

**示例 2：**

>输入：strs = ["dog","racecar","car"]
>输出：""
>解释：输入不存在公共前缀。

**提示：**

* 0 <= strs.length <= 200
* 0 <= strs[i].length <= 200
* strs[i] 仅由小写英文字母组成

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/longest-common-prefix](https://leetcode-cn.com/problems/longest-common-prefix?fileGuid=Y6VpQJjpCGWPYJXH)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

将字符串数组看作一个各行长短不一二维数组，按列遍历，如果每一列的字符相同，则将这个字符追加到最终答案末尾，否则提前返回。需要注意的是判断数组越界。

## 代码

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strs[0].length(); i++){
            char tmp = strs[0].charAt(i);
            for(int j = 1; j < strs.length; j++){
                // 这两个条件不能写反了
                if(i >= strs[j].length() || tmp != strs[j].charAt(i))
                    return sb.toString();
            }
            sb.append(tmp);
        }
        return sb.toString();
    }
}
```
## 复杂度分析

### 时间复杂度：O(mn)

m是字符串的平均长度，n位字符串个数

### 空间复杂度：O(1)

