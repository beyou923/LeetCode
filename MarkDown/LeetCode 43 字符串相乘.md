## 题目

给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

>示例 1:
>输入: num1 = "2", num2 = "3"
>输出: "6"
>示例 2:
>输入: num1 = "123", num2 = "456"
>输出: "56088"

**说明**：

* num1 和 num2 的长度小于110。
* num1 和 num2 只包含数字 0-9。
* num1 和 num2 均不以零开头，除非是数字 0 本身。
* 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。

链接：[https://leetcode-cn.com/problems/multiply-strings](https://leetcode-cn.com/problems/multiply-strings?fileGuid=kgRcHG3C6GGkYwHW)

## 思路

模拟乘法的竖式运算。该算法是通过两数相乘时，乘数某位与被乘数某位相乘，与产生结果的位置的规律来完成。具体规律如下：

* 乘数 num1 位数为 M，被乘数 num2 位数为 N， num1 x num2 结果 res 最大总位数为 M+N
* num1[i] x num2[j] 的结果为 tmp(位数为两位，"0x","xy"的形式)，其第一位位于 res[i+j]，第二位位于 res[i+j+1]

结合下图更容易理解

![图片](https://uploader.shimo.im/f/yxNqjOexlVUYSieP.png!thumbnail?fileGuid=kgRcHG3C6GGkYwHW)

```java
class Solution {
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) return "0";
        int[] res = new int[num1.length() + num2.length()];
        for(int i = num1.length() - 1; i >= 0; i--){
            int n1 = num1.charAt(i) - '0';
            for(int j = num2.length() - 1; j >= 0; j--){
                int n2 = num2.charAt(j) - '0';
                int sum = res[i + j + 1] + n1 * n2;
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < res.length; i++){
            if(i == 0 && res[i] == 0) continue; //跳过最开始的0
            sb.append(res[i]);
        }
        return sb.toString();
    }
}
```
**时间复杂度**：O(mn)
**空间复杂度**：O(m + n)

