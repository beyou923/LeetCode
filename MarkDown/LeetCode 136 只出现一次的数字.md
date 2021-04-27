## 题目描述

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

**说明：**

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

**示例 1:**

```shell
输入: [2,2,1]
输出: 1
```
**示例 2:**
```shell
输入: [4,1,2,1,2]
输出: 4
```
来源：力扣（LeetCode）
链接：[https://leetcode-cn.com/problems/single-number](https://leetcode-cn.com/problems/single-number?fileGuid=dGGHTXhgyY3gdkC3)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

用位运算(异或操作),主要用到了异或运算的两条性质:

1. 交换律: a ^ b ^ c ^ a ^ c ^ b ^ d = a ^ a ^ b ^ b ^ c ^ c ^ d = d
2. 自反律: 0 ^ a = a
## 代码

```java
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int num : nums){
            //交换律 : a ^ b ^ c ^ a ^ c ^ b ^ d = a ^ a ^ b ^ b ^ c ^ c ^ d = d
            result ^= num; //异或自反律:  0 异或任何数都等于任何数 
        }
        return result;
    }
}
```
## 复杂度分析

### 时间复杂度: O(n)

### 空间复杂度:O(1)


