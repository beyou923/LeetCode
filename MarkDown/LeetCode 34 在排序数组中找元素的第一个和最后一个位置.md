## 题目

给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。如果数组中不存在目标值 target，返回 [-1, -1]。

**进阶**：

你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？

**示例 1**：

>输入：nums = [5,7,7,8,8,10], target = 8
>输出：[3,4]

**示例 2**：

>输入：nums = [5,7,7,8,8,10], target = 6
>输出：[-1,-1]

**示例 3**：

>输入：nums = [], target = 0
>输出：[-1,-1]

**提示**：

* 0 <= nums.length <= 10^5
* -10^9 <= nums[i] <= 10^9
* nums 是一个非递减数组
* -10^9 <= target <= 10^9

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array?fileGuid=88kDThVXKTvWTKHH)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：二分查找

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length < 1) return new int[]{-1,-1};
        int[] result = new int[]{-1,-1}; 
        int start = 0, end = nums.length - 1;
        while(start <= end){
            int mid = start + (end - start) / 2;
            if(nums[mid] == target){
                int left = mid;
                int right = mid;
                // 找第一个target出现的左边界
                while(left >= start && nums[left] == target) left--;
                // 找第一个target出现的右边界
                while(right <= end && nums[right] == target) right++;
                result[0] = left + 1;
                result[1] = right - 1;
                break;
            } else if(nums[mid] > target){
                end = mid - 1;
            } else{
                start = mid + 1;
            }
        }
        return result;
    }
}
```
时间复杂度：O(logn)
空间复杂度：O(1)

