## 题目描述

给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

**示例：**

>输入：nums = [-1,2,1,-4], target = 1
>输出：2
>解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。

**提示：**

* 3 <= nums.length <= 10^3
* -10^3 <= nums[i] <= 10^3
* -10^4 <= target <= 10^4

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/3sum-closest](https://leetcode-cn.com/problems/3sum-closest?fileGuid=tKQP6HRHJwJyWpgd)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

本题思路类似[LeetCode 15](https://leetcode-cn.com/problems/3sum?fileGuid=tKQP6HRHJwJyWpgd)。**排序 + 双指针**：首先将数组按照从小到大排序，从头开始遍历数组，每轮固定当前元素nums[i]，用2个指针分别指向当前元素的下一个元素nums[left]和最后一个元素nums[right]，计算3个元素之和sum = nums[i] + nums[left] + nums[right]，计算sum与target差的绝对值，并于上一轮差的绝对值diff对比，如果diff小于本轮差的绝对值，则，更新diff为本轮差的绝对值，同时将closest更新为sum。如果sum < target 则移动左指针；否则移动右指针。为了提高效率可以做一些剪枝操作。

## 代码

```java
import java.util.Arrays;
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = Integer.MIN_VALUE;
        int diff = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length - 2; i++){
            int left = i + 1, right = nums.length - 1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(diff > Math.abs(target - sum)) {
                    diff = Math.abs(target - sum);
                    closest = sum;
                }
                if(sum < target){
                    left++;
                    // 跳过重复的nums[left]
                    // TODO：是nums[left] == nums[left - 1] 不是nums[left] == nums[left + 1],进入循环left已加1
                    while(left < right && nums[left] == nums[left - 1])
                        left++;
                }else{
                    right--;
                    // 跳过重复的nums[right]
                    // TODO：是nums[right] == nums[right + 1] 不是nums[right] == nums[right - 1],进入循环left已减1
                    while(left < right && nums[right] == nums[right + 1])
                        right--;
                }
            }
            // 跳过重复的nums[i]
            while(i < nums.length - 2 && nums[i] == nums[i + 1])
                i++;
        }
        return closest;
    }
}
```
## 复杂度分析

### 时间复杂度分析：O(n^2)

### 空间复杂度：O(1)

