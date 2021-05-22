## 题目

给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。返回滑动窗口中的最大值。

**示例 1**：

>输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
>输出：[3,3,5,5,6,7]
>解释：
>滑动窗口的位置                最大值
>---------------               -----
>[1  3  -1] -3  5  3  6  7       3
>1 [3  -1  -3] 5  3  6  7       3
>1  3 [-1  -3  5] 3  6  7       5
>1  3  -1 [-3  5  3] 6  7       5
>1  3  -1  -3 [5  3  6] 7       6
>1  3  -1  -3  5 [3  6  7]      7

**示例 2**：

>输入：nums = [1], k = 1
>输出：[1]

**示例 3**：

>输入：nums = [1,-1], k = 1
>输出：[1,-1]

**示例 4**：

>输入：nums = [9,11], k = 2
>输出：[11]

**示例 5**：

>输入：nums = [4,-2], k = 2
>输出：[4]

**提示**：

* 1 <= nums.length <= 10^5
* -10^4 <= nums[i] <= 10^4
* 1 <= k <= nums.length

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/sliding-window-maximum](https://leetcode-cn.com/problems/sliding-window-maximum?fileGuid=WkXxVK6TgqxTPX3P)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：滑动窗口 + 单调队列

本题与[《剑指offer》59](https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/?fileGuid=WkXxVK6TgqxTPX3P)题一样，需要用单调队列维护滑动窗口中的最大值。这道题一开始的想法是打算用优先级队列实现（大根堆），也就是维护一个大小为k的大根堆，每次滑动窗口时读取堆顶元素，同时将滑出窗口的元素从堆中删除，并将滑入窗口的元素插入堆中。在窗口比较小的时候这种方法可以，但是窗口大了，就需要频繁地从堆中删除、插入元素，时间开销比较大。最优解是用单调队列实现，单调队列基于双端队列实现，队列头部是窗口内元素的最大值，每次滑动窗口的时候只需要取出对头元素即可，设计单调队列的时候，pop和push操作要保持如下两条 规则：

* pop(value)：如果窗口移除的元素value等于单调队列的出口元素，那么队列弹出元素，否则不用任何操作
* push(value)：如果push的元素value大于入口元素的数值，那么就将队列入口的元素弹出，直到push元素的数值小于等于队列入口元素的数值为止
```java
public class Solution {
   // 滑动窗口 + 单调队列（基于双端队列实现）
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>(); // 双端队列
        // 窗口的左右边界
        int start = 0, end = 0;
        int[] ans = new int[nums.length - k + 1];
        // 滑动窗口
        while (end < nums.length){
            int value = nums[end];
            // 如果当前元素大于队尾元素，那么当前元素就有可能成为滑动窗口的最大值，需要将其移动到尽量靠前的位置
            while (!deque.isEmpty() && value > deque.getLast()){
                deque.pollLast();
            }
            deque.offerLast(value);
            // 窗口已满
            if (end - start + 1 == k){
                // 对头元素就是当前窗口的最大值
                int max = deque.getFirst();
                ans[start] = max;
                // 如果移出窗口的元素就是最大值，需要将对头元素弹出队列
                if (max == nums[start])
                    deque.pollFirst();
                start++; // 左边界向右移动
            }
            end++;
        }
        return ans;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(k)

