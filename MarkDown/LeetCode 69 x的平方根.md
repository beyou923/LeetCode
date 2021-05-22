## 题目

实现 int sqrt(int x) 函数。计算并返回 x 的平方根，其中 x 是非负整数。由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

**示例 1**:

>输入: 4
>输出: 2

**示例 2**:

>输入: 8
>输出: 2
>说明: 8 的平方根是 2.82842...,
>由于返回类型是整数，小数部分将被舍去。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/sqrtx](https://leetcode-cn.com/problems/sqrtx?fileGuid=ppkvxRpjQcYgdHqw)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：二分查找

x的平方根一定在[1,x]范围内，

如果**i * i <= x**则 i 有可能成为x的平方根；

如果**i * i > x**则 i 绝对不可能是x的平方根

由于区间有序可以在[1,x]内用二分查找寻找x的平方根。

如果**i * i <= x**则 i 有可能成为x的平方根，记录下中间数 mid,同时到右半段继续二分查找；

如果**i * i > x**则 i 绝对不可能是x的平方根；到左半段继续二分查找

```java
class Solution {
    public int mySqrt(int x) {
        if(x == 0) return 0;
        if(x == 1) return 1;
        int start = 1, end = x / 2, ans = 0;
        while(start <= end){
            int mid = start + (end - start) / 2;
            if((long)mid * mid <= x) {
                // mid有可能成为x的平方根
                ans = mid;
                start = mid + 1;
            } else end = mid - 1;
        }
        return ans;
    }
}
```
时间复杂度：O(logn)
空间复杂度：O(1)

