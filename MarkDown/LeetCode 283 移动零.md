## 题目描述

给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

**示例:**

```python
输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
```
**说明:**
1. 必须在原数组上操作，不能拷贝额外的数组。
2. 尽量减少操作次数。

链接：[https://leetcode-cn.com/problems/move-zeroes](https://leetcode-cn.com/problems/move-zeroes?fileGuid=cgxPxHxHYwhcj3GD)

## 思路

双指针法。定义2个指针`i`和`j`,初始时`i`指向第一个元素，`j`指向第二个元素，分4种情况讨论：

nums[i] = 0 && nums[j] != 0 ，则交换两个指针指向的元素，i、j后移；

nums[i] != 0 && nums[j] = 0 或者 nums[i] != 0 && nums[j] != 0，则i、j后移；

nums[i] = 0 && nums[j] = 0 ，则j向后移动

## 代码

```java
class Solution {
    public void moveZeroes(int[] nums) {
        // 双指针法
        int len = nums.length;
        if(len < 2) return;
        int i = 0, j = 1;
        while (i < j && j <= len -1){
            if (nums[i] == 0 && nums[j] != 0){
                nums[i] = nums[j];
                nums[j] = 0;
                i++;
                j++;
            } else if (nums[i] != 0 && nums[j] != 0 || nums[i] != 0 && nums[j] == 0){
                i++;
                j++;
            } else if (nums[i] == 0 && nums[j] == 0){
                j++;
            }
        }
    }
}
```
## 复杂度分析

时间复杂度：O(n)

空间复杂度：O(1)

