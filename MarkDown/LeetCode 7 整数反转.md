## 题目描述

给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。假设环境不允许存储 64 位整数（有符号或无符号）。

**示例 1：**

>输入：x = 123
>输出：321

示例 2：

```plain
输入：x = -123
输出：-321
```
**示例 3：**
>输入：x = 120
>输出：21

**示例 4：**

>输入：x = 0
>输出：0

**提示：**

* -2^31 <= x <= 2^31 - 1

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/reverse-integer](https://leetcode-cn.com/problems/reverse-integer?fileGuid=JHqPqx8pvjrWX6Y6)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

设置一个int型变量result，初始时候等于0，在while循环中对x取模，得到最后一位，再用x除以10得到除掉末尾一位后的数，更新result = result * 10 + x % 10，需要注意的是判断result是否溢出，具体判断方法如下（参考[这里](https://leetcode-cn.com/problems/reverse-integer/solution/tu-jie-7-zheng-shu-fan-zhuan-by-wang_ni_ma/?fileGuid=JHqPqx8pvjrWX6Y6)）：

![图片](https://uploader.shimo.im/f/hsBLi6AcKt2T2aMa.png!thumbnail?fileGuid=JHqPqx8pvjrWX6Y6)

上图中，绿色的是最大32位整数。第二排数字中，橘色的是5，它是大于上面同位置的4，这就意味着5后跟任何数字，都会比最大32为整数都大。所以，我们到【最大数的1/10】时，就要开始判断了，如果某个数字大于 214748364那后面就不用再判断了，肯定溢出了；如果某个数字等于 214748364呢，这对应到上图中第三、第四、第五排的数字，需要要跟最大数的末尾数字比较，如果这个数字比7还大，说明溢出了。

对于负数也是一样的

![图片](https://uploader.shimo.im/f/OngXcVMKqJnuirax.png!thumbnail?fileGuid=JHqPqx8pvjrWX6Y6)

作者：wang_ni_ma

链接：[https://leetcode-cn.com/problems/reverse-integer/solution/tu-jie-7-zheng-shu-fan-zhuan-by-wang_ni_ma/](https://leetcode-cn.com/problems/reverse-integer/solution/tu-jie-7-zheng-shu-fan-zhuan-by-wang_ni_ma/?fileGuid=JHqPqx8pvjrWX6Y6)

来源：力扣（LeetCode）

著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

## 代码

```java
class Solution {
    public static int reverse(int x) {
        int result = 0;
        while(x != 0){
            int end = x % 10;
            if(Math.abs(result) > Integer.MAX_VALUE / 10)
                return 0;
            if (Math.abs(result) == Integer.MAX_VALUE / 10 && (end > 7 || end < -8))
                return 0;
            result = result * 10 + end;
            x = x / 10;
        }
        return result;
    }
    public static void main(String[] args){
        int x = -25;
        int res = reverse(x);
        System.out.println(res);
    }
}
```
## 复杂度分析

### 时间复杂度：O($$log_{10}x$$)

x中大概有$$log_{10}x$$位数字

### 空间复杂度：O(1)

