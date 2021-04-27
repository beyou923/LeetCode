## 题目描述

整数数组 nums 按升序排列，数组中的值 互不相同 。在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如，[0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。给你旋转后的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的索引，否则返回 -1 。

**示例 1：**

```shell
输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4
```
**示例 2：**
```shell
输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1
```
**示例 3：**
```shell
输入：nums = [1], target = 0
输出：-1
```
**提示：**
* 1 <= nums.length <= 5000
* -10^4 <= nums[i] <= 10^4
* nums 中的每个值都 独一无二
* nums 肯定会在某个点上旋转
* -10^4 <= target <= 10^4

**进阶**：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？

链接：[https://leetcode-cn.com/problems/search-in-rotated-sorted-array](https://leetcode-cn.com/problems/search-in-rotated-sorted-array?fileGuid=TyTh3DxvDKPcJW8R)

## 思路

这个题是二分查找的变种，主要分3步实现。

1. 确定中点
2. 确定target属于哪一段
3. 在选定的段中用二分查找算法
```java
class Solution {
    public int search(int[] nums, int target) {
        int len = nums.length;
        int low = 0, hight = len -1;
        while(low <= hight){
            int mid = low + (hight - low) / 2;
            if(nums[mid] == target)
                return mid;
            if(nums[mid] < nums[hight]){
                if(nums[mid] < target && target <= nums[hight]){
                    low = mid + 1;
                }else{
                    hight = mid -1;
                }
            } else{
                if(nums[low] <= target && target < nums[mid]){
                    hight = mid -1;
                } else{
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}
```
**时间复杂度**：O(logn)
**空间复杂度**：O(1)

