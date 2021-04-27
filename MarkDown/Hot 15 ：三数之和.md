## 题目描述

给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。注意：答案中不可以包含重复的三元组。

**示例 1：**

```shell
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
```
**示例 2：**
```shell
输入：nums = []
输出：[]
```
**示例 3：**
```shell
输入：nums = [0]
输出：[]
```
**提示：**
1. 0 <= nums.length <= 3000
2. -105 <= nums[i] <= 105

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/3sum](https://leetcode-cn.com/problems/3sum)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：

排序 + 双指针：首先将数组按照从小到大排序，从头开始遍历数组，每轮固定当前元素`nums[i]`，用2个指针分别指向当前元素的下一个元素`nums[left]`和最后一个元素`nums[right]`，计算3个元素之和`sum = nums[i] + nums[left] + nums[right]`，如果`sum == 0`则将这3个数加入列表，同时移动左右指针（为反之重复组合出现在最终答案中，在移动左右指针时候要跳过所有相同元素）；如果`sum < 0`则移动左指针；否则移动右指针。为了提高效率可以做一些剪枝操作。

## 代码

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if(len < 3)  // 数组长度小于3肯定没有答案
            return res;
        Arrays.sort(nums); // 数组排序（升序）
        for(int i = 0; i < len; i++){
            if(nums[i] > 0) // 当前元素大于0，说明后面的元素也大于0，不可能有答案 剪枝
                break;
            if(i > 0 && nums[i -1] == nums[i]) // 防止重复  剪枝
                continue;
            int left = i + 1, right = len - 1;
            while(left < right){
                int leftValue = nums[left], rightValue = nums[right];
                int sum = nums[i] + leftValue + rightValue;
                if(sum == 0){
                    res.add(Arrays.asList(nums[i],leftValue,rightValue)); // 注意这个方法
                    while(left < right && nums[left] == leftValue) // 跳过重复元素
                        left++;
                    while(left < right && nums[right] == rightValue) // 跳过重复元素
                        right--;
                } else if(sum < 0) // 左指针移动
                    left++;
                else
                    right--;  // 右指针移动
            }
        }
        return res;
    }
}
```
## 复杂度分析

### 时间复杂度：O(n^2)

### 空间复杂度：O(1)

