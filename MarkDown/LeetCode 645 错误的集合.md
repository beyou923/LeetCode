## 题目描述

集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合丢失了一个数字 并且 有一个数字重复 。给定一个数组 nums 代表了集合 S 发生错误后的结果。请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。

**示例 1：**

```shell
输入：nums = [1,2,2,4]
输出：[2,3]
```
**示例 2：**
```shell
输入：nums = [1,1]
输出：[1,2]
```
**提示：**
* 2 <= nums.length <= 10^4
* 1 <= nums[i] <= 10^4

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/set-mismatch](https://leetcode-cn.com/problems/set-mismatch?fileGuid=TCXKHh36RKWTKHq8)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

本题是LeetCode 442 和 [LeetCode 448](https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/?fileGuid=TCXKHh36RKWTKHq8)的综合,方法都是利用1 ≤ a[i] ≤ n  这个特性, 可以利用数组下标特性标记数字是否出现过.

## 代码

```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] result = new int[2];
        for(int num : nums){
            int index = Math.abs(num) - 1;
            if(nums[index] < 0){
                // 重复出现的数字
                result[0] = index + 1;
            } else{
                nums[index] = -nums[index];
            }
        }
        // 寻找消失的数字
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0)
                result[1] = i + 1;
        }
        return result;
    }
}
```
## 复杂度分析

### 时间复杂度:O(n)

### 空间复杂度:O(1)

