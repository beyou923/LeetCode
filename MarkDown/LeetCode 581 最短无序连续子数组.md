## 题目

给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

请你找出符合题意的 最短 子数组，并输出它的长度。

**示例 1**：

>输入：nums = [2,6,4,8,10,9,15]
>输出：5
>解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。

**示例 2**：

>输入：nums = [1,2,3,4]
>输出：0

**示例 3**：

>输入：nums = [1]
>输出：0

**提示**：

* 1 <= nums.length <= 10^4
* -10^5 <= nums[i] <= 10^5

**进阶**：你可以设计一个时间复杂度为 O(n) 的解决方案吗？

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray](https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray?fileGuid=rWhdHVXDY8wyD8Y9)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 方法一：排序

可以将原数组排序，将排序后的结果放在一个辅助数组中，第一次从前往后遍历，第一个不同元素的位置就是无序子数组的起始位置；第二次从后往前遍历，第一个不同元素就是无序子数组的结束位置，最后返回结束位置减去开始位置之差加1即可

时间复杂度：O(nlogn)

空间复杂度：O(n)

### 方法二：4趟遍历

* 第一次从前往后遍历找到无序区间的最小值min
* 第二次从后往前遍历找到无需区间的最大值max
* 第三次从前往后遍历找到第一个大于无序区间最小值的元素的位置，记为start，这个位置就是无序区间的起始位置
* 第四次从后往前遍历找到第一个小于无序区间的最大值的元素的位置，记为end，这个位置就是无序区间的结束位置
* 最后返回 end - start <= 0 ? 0 : end -start + 1
```java
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
        // 求无序（降序）区间的最小值
        for(int i = 1; i < nums.length ; i++){
            if(nums[i] < nums[i - 1])
                flag = true; // 降序
            if(flag == true)
                min = Math.min(min, nums[i]);
        }
        flag = false;
        // 求无序(降序)区间的最大值
        for(int j = nums.length - 2; j >= 0; j--){
            if(nums[j] > nums[j + 1])
                flag = true;
            if(flag == true)
                max = Math.max(max, nums[j]);
        }
        int start = 0, end = 0;
        // 找无序区间的起始位置, 哪个位置是无序区间的起始位置？
        // TODO: 第一个大于无序区间最小值的元素所在的位置
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > min){
                start = i;
                break;
            }  
        }
        // 找无需区间的结束位置，哪个位置是无序区间的结束位置？
        // TODO: 第一个小于无序区间最大值的元素所在的位置
        for(int j = nums.length - 1; j >= 0; j--){
            if(nums[j] < max){
                end = j;
                break;
            }
        }
        return end - start <= 0 ? 0 : end -start + 1;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(1)

