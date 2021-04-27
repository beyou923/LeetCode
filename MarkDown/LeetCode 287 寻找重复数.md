## 题目

给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。

**示例 1**：

>输入：nums = [1,3,4,2,2]
>输出：2

**示例 2**：

>输入：nums = [3,1,3,4,2]
>输出：3

**示例 3**：

>输入：nums = [1,1]
>输出：1

**示例 4**：

>输入：nums = [1,1,2]
>输出：1

**提示**：

* 2 <= n <= 3 * 10^4
* nums.length == n + 1
* 1 <= nums[i] <= n
* nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次



**进阶**：

* 如何证明 nums 中至少存在一个重复的数字?
* 你可以在不修改数组 nums 的情况下解决这个问题吗？
* 你可以只用常量级 O(1) 的额外空间解决这个问题吗？
* 你可以设计一个时间复杂度小于 O(n2) 的解决方案吗？

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/find-the-duplicate-number](https://leetcode-cn.com/problems/find-the-duplicate-number?fileGuid=6vhYtcdh9WkYRq6p)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 方法一：二分查找

在区间 [1, n] 进行二分查找，首先求出中点 mid，然后遍历整个数组，统计所有小于等于 mid 的数的个数，如果个数小于等于 mid，则说明重复值在 [mid+1, n] 之间，反之，重复值应在 [1, mid-1] 之间，然后依次类推，直到搜索完成，此时的 left就是我们要求的重复值

```java
class Solution {
    // 二分查找法
    public int findDuplicate(int[] nums) {
        // 二分查找的初始区间 [1,n-1) 也就是[1,n]
        int left = 1, right = nums.length;
        while(left < right){
            int mid = left + (right - left) / 2, cnt = 0;
            for(int num : nums){
                if(num <= mid) cnt++;
            }
            if(cnt <= mid) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
```
时间复杂度：O(nlogn)
空间复杂度：O(1)

### 方法二：快慢指针法

我们对 nums 数组建图，每个位置 i 连一条 i→nums[i] 的边。由于存在的重复的数字 target，因此target 这个位置一定有起码两条指向它的边，因此整张图一定存在环，且我们要找到的 target 就是这个环的入口，那么整个问题就等价于[LeetCode 141](https://leetcode-cn.com/problems/linked-list-cycle/?fileGuid=6vhYtcdh9WkYRq6p)和[ LeetCode 142](https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode/?fileGuid=6vhYtcdh9WkYRq6p)题。

我们先设置慢指针slow 和快指针 fast ，慢指针每次走一步，快指针每次走两步，根据「Floyd 判圈算法」两个指针在有环的情况下一定会相遇，此时我们再将 slow 放置起点 0，两个指针每次同时移动一步，相遇的点就是答案。

关于如何对 nums 数组建图的问题，可以把数组想象成一个链表，数组下标抽象为指向下一个结点的指针，链表元素抽象为结点值，数组下标0表示链表头结点。以[1,3,4,2,2]为例可以构建如下链表

![图片](https://uploader.shimo.im/f/xTXH1ypy1hFis3gw.png!thumbnail?fileGuid=6vhYtcdh9WkYRq6p)

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do{
            // 慢指针移动一步；快指针移动2步
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);
        // 快慢指针相遇后将慢指针指向数组第一个元素，
        // 快慢指针同时移动一步，直到相遇
        slow = 0;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(1)




