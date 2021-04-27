# LeetCode 231 2的幂

## 题目

给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

**示例 1**:

>输入: 1
>输出: true
>解释: 20 = 1

**示例 2**:

>输入: 16
>输出: true
>解释: 24 = 16

**示例 3**:

>输入: 218
>输出: false

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/power-of-two](https://leetcode-cn.com/problems/power-of-two?fileGuid=DdKwkJypC6wj8QYX)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 方法一：循环

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n < 1) return false;
        while(n % 2 == 0){
            n /= 2;
        }
        return n == 1;
    }
}
```
### 方法二：位运算

若 n = 2^x 且 x 为自然数（即 n 为 2 的幂），则一定满足以两个下条件：

1. 恒有 n & (n - 1) == 0，这是因为：n 二进制最高位为 1，其余所有位为 0；n−1 二进制最高位为 0，其余所有位为 1；
2. 一定满足 n > 0。

因此，通过 n > 0 且 n & (n - 1) == 0 即可判定是否满足 n = 2^x

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```
**时间复杂度**：O(1)
**空间复杂度**：O(1)

# LeetCode 326 3的幂

## 题目

给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3^x

**示例 1**：

>输入：n = 27
>输出：true

**示例 2**：

>输入：n = 0
>输出：false

**示例 3**：

>输入：n = 9
>输出：true

**示例 4**：

>输入：n = 45
>输出：false

**提示**：

* -2^31 <= n <= 2^31 - 1

**进阶**：

* 你能不使用循环或者递归来完成本题吗？

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/power-of-three](https://leetcode-cn.com/problems/power-of-three?fileGuid=DdKwkJypC6wj8QYX)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 方法一：循环

3^b == n，肯定有n 大于0，且n = 3 * 3 * 3 *...*3 (一共b个3)，可以不停地对n % 3判断是否等于0，如果等于0让 n /= 3，最后判断n 是否等于1，如果等于1返回true；否则返回false

```java
class Solution {
    public boolean isPowerOfThree(int n) {
        if(n < 1) return false;
        while(n % 3 == 0){
            n /= 3;
        }
        return n == 1;
    }
}
```
**时间复杂度**：O(logn)
**空间复杂度**：O(1)

### 方法二：整数限制

`n`的类型是`int`而且大于0在，Java 中说明了该变量是四个字节，他的最大值为 2147483647 ，知道了`n`的限制，我们现在可以推断出`n`的最大值，也就是 3 的幂，是 1162261467。我们计算如下：

$$3^{log_{3}Integer.MAX_VALUE} = 3^{19.56} = 3^{19} = 1162261467$$

```java
class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
```
**时间复杂度**：O(1)
**空间复杂度**：O(1)

# LeetCode 342 4的幂

## 题目

给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4^x

**示例 1**：

>输入：n = 16
>输出：true

**示例 2**：

>输入：n = 5
>输出：false

**示例 3**：

>输入：n = 1
>输出：true

**提示**：

* -2^31 <= n <= 2^31 - 1

**进阶**：

* 你能不使用循环或者递归来完成本题吗？

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/power-of-four](https://leetcode-cn.com/problems/power-of-four?fileGuid=DdKwkJypC6wj8QYX)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思想

### 方法一：循环

类似上一题

```java
class Solution {
    public boolean isPowerOfFour(int n) {
        if(n < 1) return false;
        while(n % 4 == 0){
            n /= 4;
        }
        return n == 1;
    }
}
```
### 方法二：位运算

**算法**：

* 我们首先检查 num 是否为 2 的幂：x > 0 and x & (x - 1) == 0。
* 现在的问题是区分 2 的偶数幂（当 x 是 4 的幂时）和 2 的奇数幂（当 x 不是 4 的幂时）。在二进制表示中，这两种情况都只有一位为 1（最高位），其余为 0。
* 有什么区别？在第一种情况下（4 的幂），1 处于偶数位置：第 0 位、第 2 位、第 4 位等；在第二种情况下，1 处于奇数位置。

![图片](https://uploader.shimo.im/f/8rmq5eUHoo7IpkTN.png!thumbnail?fileGuid=DdKwkJypC6wj8QYX)

* 因此 4 的幂与二进制的 (101010...10) 进行按位与运算会得到 0。即 4^a  & (101010...10)== 0
* 二进制的(101010...10)用十六进制表示为 ：0xaaaaaaaa
```java
class Solution {
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n -1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }
}
```
