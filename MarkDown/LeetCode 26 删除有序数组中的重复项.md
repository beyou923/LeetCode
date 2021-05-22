## 题目

给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

**说明**:

为什么返回数值是整数，但输出的答案是数组呢?

请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

>// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
>int len = removeDuplicates(nums);
>>// 在函数里修改输入数组对于调用者是可见的。
>// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
>for (int i = 0; i < len; i++) {
>print(nums[i]);
>}

**示例 1**：

>输入：nums = [1,1,2]
>输出：2, nums = [1,2]
>解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。

**示例 2**：

>输入：nums = [0,0,1,1,1,2,2,3,3,4]
>输出：5, nums = [0,1,2,3,4]
>解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。

**提示**：

* 0 <= nums.length <= 3 * 10^4
* -10^4 <= nums[i] <= 10^4
* nums 已按升序排列

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array?fileGuid=qxVCQvYxwC6dJHvP)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思想：双指针法

由于数组有序，那么重复元素必定是连续出现的，定义两个指针p和q，初始时候p 指向第一个元素，q指向第二个元素，分下面两种情况讨论：

* 如果p和q指向元素相等，只移动q指针
* 如果p和q指向元素不相等，将q指向元素赋值给p的下一个元素

返回p + 1

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length < 2) return nums.length;
        // p指向需要删除的元素 q遍历数组
        int p = 0, q = 1;
        while(q < nums.length){
            if(nums[p] != nums[q]){
                nums[++p] = nums[q];
            }
            q++;
        }
        // [0,p]范围内就是不重复的元素，则新数组长度就是 p + 1
        return p + 1;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(1)

