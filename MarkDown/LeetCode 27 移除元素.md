## 题目

给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

**说明**:

为什么返回数值是整数，但输出的答案是数组呢?请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

>// nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
>int len = removeElement(nums, val);
>>// 在函数里修改输入数组对于调用者是可见的。
>// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
>for (int i = 0; i < len; i++) {
>print(nums[i]);
>}

**示例 1**：

>输入：nums = [3,2,2,3], val = 3
>输出：2, nums = [2,2]
>解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。

**示例 2**：

>输入：nums = [0,1,2,2,3,0,4,2], val = 2
>输出：5, nums = [0,1,4,0,3]
>解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。

**提示**：

* 0 <= nums.length <= 100
* 0 <= nums[i] <= 50
* 0 <= val <= 100

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/remove-element](https://leetcode-cn.com/problems/remove-element?fileGuid=3YcXc3V89qqGhkQw)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：双指针

### 方法一

定义两个指针slow和fast，其中slow指向不等于val的元素，fast遍历数组

* 如果数组元素不等于val，将fast指向的元素放入slow指向的位置，同时slow和fast向后移动一个位置；
* 如果fast指向位置元素等于val，只移动fast
* 最终slow的大小就是去除val后的数组长度

初始时slow和fast都指向0

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        if(nums.length < 1) return 0;
        int slow = 0;
        for(int fast = 0; fast < nums.length; fast++){
            if(nums[fast] != val)
                nums[slow++] = nums[fast];
        }
        return slow;
    }
}
```
### 方法二

定义两个指针left和right，初始时分别指向第一个和最后一个元素。

* 如果left指向的元素不等于val，则left右移；
* 如果right指向元素等于val，则right左移；
* 如果左边元素等于val，右边元素不等于val才可以覆盖左边元素；

最后，判断left和right是否相等：

* 如果left > right，返回left
* 如果 left == right 且 nums[left] == val ，返回 left；否则，返回 left + 1
```java
class Solution {
    public int removeElement(int[] nums, int val) {
        if(nums.length < 1) return 0;
        int left = 0, right = nums.length - 1;
        while(left < right){
            while(left < right && nums[left] != val) left++;
            while(left < right && nums[right] == val) right--;
            // 只有左边元素等于val，右边元素不等于val才可以覆盖左边元素
            // 防止左右两个元素相同时也覆盖左边元素
            if(nums[left] == val && nums[right] != val)
                nums[left++] = nums[right--];
        }
        // 退出上面的while循环有两种可能: left == right or left > right
        if(left > right) return left;
        if(nums[left] == val) {
            return left;
        }
        return left + 1;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(1)

