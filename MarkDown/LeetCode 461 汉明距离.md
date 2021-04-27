## 题目描述

两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。

给出两个整数 x 和 y，计算它们之间的汉明距离。

**注意：**

>0 ≤ x, y < 231.

**示例:**

>输入: x = 1, y = 4
>输出: 2
>解释:
>1   (0 0 0 1)
>4   (0 1 0 0)
>↑   ↑
>上面的箭头指出了对应二进制位不同的位置。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/hamming-distance](https://leetcode-cn.com/problems/hamming-distance?fileGuid=8cGRdhp6XCc83JKX)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路:位运算

将x和y异或,得到结果xor的二进制表示中1的个数就是汉明距离,现在问题转化成求xor二进制表示中1的个数.循环中将xor右移1位并于0x01按位与运算,如果结果等于1,则距离distance加1,直到xor变为0为止.

## 代码

### 手撕版

```java
class Solution {
    public int hammingDistance(int x, int y) {
        int distance = 0;
        int xor = x ^ y; // 按位异或或xor的二进制表示中1的个数就是汉明距离
        while(xor != 0){ // 求出xor二进制表示中1的个数
            int and = xor & 0x1;
            if(and == 1) distance++;
            xor = xor >> 1;
        }
        return distance;
    }
}
```
### API版

```java
class Solution {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
```
## 复杂度分析

### 时间复杂度：O(1)

int位数恒定,在while循环中移动的次数就是恒定的

### 空间复杂度：O(1)

