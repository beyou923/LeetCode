## 题目描述

给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。你可以假设数组是非空的，并且给定的数组总是存在多数元素。

**示例 1：**

```shell
输入：[3,2,3]
输出：3
```
**示例 2：**
```shell
输入：[2,2,1,1,1,2,2]
输出：2
```
**进阶：**
* 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/majority-element](https://leetcode-cn.com/problems/majority-element?fileGuid=qyyvRtggRqqpGvXx)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路:摩尔投票算法

本题与[《剑指offer》39题](https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/?fileGuid=qyyvRtggRqqpGvXx)一样。算法主要流程：

1. 定义两个变量`major`和`count`分别记录出现次数大于`n/2`的元素和该元素出现的次数，初始时都等于0
2. 变量数组，如果`count`等于0就将当前元素`num`赋值给`major`；否则需要判断`major`是否等于`num`，如果相等则`count`加1，否则减1。
3. 返回`major`
## 代码

```java
class Solution {
    // 与剑指offer 39题相同
    public int majorityElement(int[] nums) {
        // 摩尔投票法 major就是出现次数大于 n/2的数;count记录major出现的次数
        int major = 0, count = 0;
        for(int num : nums){
            if(count == 0) // major次数减为0,将当前元素设为major
                major = num;
            count += major == num ? 1 : -1; // major与当前元素相等次数加1,否则次数减1
        }
        return major;
    }
}
```
## 复杂度分析

### 时间复杂度: O(n)

### 空间复杂度: O(1)

