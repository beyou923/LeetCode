## 题目描述

给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按任意顺序返回。给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

![图片](https://uploader.shimo.im/f/XOS8bDxEnb3pYQgR.png!thumbnail?fileGuid=qqrKGRrhWYcPDkkv)

**示例 1：**

```shell
输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
```
**示例 2：**
```shell
输入：digits = ""
输出：[]
```
**示例 3：**
```shell
输入：digits = "2"
输出：["a","b","c"]
```
**提示：**
* 0 <= digits.length <= 4
* digits[i] 是范围 ['2', '9'] 的一个数字。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

涉及到组合问题一般首先想到[回溯法](https://mp.weixin.qq.com/s?__biz=MzUxNjY5NTYxNA==&mid=2247485237&idx=1&sn=1bae4c3d0d3965af44878093a5a49f58&scene=21#wechat_redirect)。假设输入的数字为"23"，可以抽象为下面的树形结构

![图片](https://uploader.shimo.im/f/jDiEmUxtuSj2y80u.png!thumbnail?fileGuid=qqrKGRrhWYcPDkkv)

图中可以看出遍历的深度，就是输入"23"的长度，而叶子节点就是我们要收集的结果，输出["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]。

## 代码

```java
class Solution {
public List<String> letterCombinations(String digits) {
List<String> res = new ArrayList<String>();
int len = digits.length();
if(len < 1) return res;
Map<Character,String> map = new HashMap<Character,String>(){
{
put('2',"abc");
put('3',"def");
put('4', "ghi");
put('5', "jkl");
put('6', "mno");
put('7',"pqrs");
put('8',"tuv");
put('9',"wxyz");

}
};
dfs(res,digits,0,map,new StringBuilder());
return res;
}
public void dfs(List<String> res, String digits, int index,Map<Character,String> map,StringBuilder str){
if(index == digits.length()){
res.add(str.toString());
return;
}
char digit = digits.charAt(index);
String letters = map.get(digit);
for(int i = 0; i < letters.length(); i++){
str.append(letters.charAt(i));
dfs(res,digits,index + 1, map,str);
str.deleteCharAt(index);// 回溯
}
}
}
```
## 复杂度分析

### 时间复杂度

O(3^m x 4^n)，其中m是输入中对应3个人字母的数字个数，n是输入中对应4个字母的数字

### 空间复杂度

O(m + n)，其中m是输入中对应3个人字母的数字个数，n是输入中对应4个字母的数字。空间复杂度主要取决于递归深度，递归深度最大为m + n。

