## 题目

给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

**示例**:

>输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
>输出:
>[
>["ate","eat","tea"],
>["nat","tan"],
>["bat"]
>]

**说明**：

* 所有输入均为小写字母。
* 不考虑答案输出的顺序。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/group-anagrams](https://leetcode-cn.com/problems/group-anagrams?fileGuid=6t3Wx3DHrJk9KpRq)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

遍历字符串数组中每个字符串，将每个字符串排序，组成异位词的字母相同，排序后得到的字符串也相同，定义一个map，用排序后的字符串作为key，map的value是一个list，将排序前的字符串加入list，最后将map中的所有值加入返回

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length < 1) return null;
        Map<String, List<String>> word = new HashMap<>();
        for(String s : strs){
            char[] tmp = s.toCharArray();
            Arrays.sort(tmp);
            String key = new String(tmp);
            List<String> list = word.getOrDefault(key,new ArrayList<String>());
            list.add(s);
            word.put(key,list);
        }
        return new ArrayList<List<String>>(word.values());
    }
}
```
时间复杂度：O(nklogk)，其中，n为字符串数组长度，k为字符串的长度
空间复杂度：O(nk)

