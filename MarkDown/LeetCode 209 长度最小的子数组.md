## 题目

给定一个含有 n 个正整数的数组和一个正整数 target 。找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。

**示例 1**：

>输入：target = 7, nums = [2,3,1,2,4,3]
>输出：2
>解释：子数组 [4,3] 是该条件下的长度最小的子数组。

**示例 2**：

>输入：target = 4, nums = [1,4,4]
>输出：1

**示例 3**：

>输入：target = 11, nums = [1,1,1,1,1,1,1,1]
>输出：0



**提示**：

* 1 <= target <= 10^9
* 1 <= nums.length <= 10^5
* 1 <= nums[i] <= 10^5



**进阶**：

* 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/minimum-size-subarray-sum](https://leetcode-cn.com/problems/minimum-size-subarray-sum?fileGuid=rjYqp8hHYhHJqvw6)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

滑动窗口法，不断调节子数组的起始位置和终止位置，得到最终结果，初始时候滑动窗口的起始位置和终止位置都是数组的第一个元素，只要窗口内的元素累加和小于target，就固定窗口的其实位置，每次只移动窗口的结束位置，并累加元素，如果子数组类元素累加和大于等于target时，固定住子数组的结束位置，只移动数组的其实位置，每移动一次就从累加和中减掉移出窗口的元素，计算连续子数组的长度，重复上述过程

```java
public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        int sum = 0; // 连继子数组的和
        int i = 0; // 连续子数组的起始位置
        int subLength = 0; // 连续子数组的长度
        for(int j = 0; j < nums.length; j++){
            sum += nums[j];
            while(sum >= target){
                subLength = j -i + 1; // 当前最短连续子数组的长度
                result = Math.min(result, subLength);
                sum -= nums[i++]; // 改变最短子数组的起始位置 
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(1)

