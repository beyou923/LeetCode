## 题目描述

在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

**示例 1:**

```shell
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
```
**示例 2:**
```shell
输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
```
**说明:**
* 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

链接：[https://leetcode-cn.com/problems/kth-largest-element-in-an-array](https://leetcode-cn.com/problems/kth-largest-element-in-an-array?fileGuid=DdKHVDP6c9HVGVGg)

## 思路

### 方法一：排序法

先对数组排序（快速排序），在访问第k大的数 nums[len - k]，其中len为数组长度，这种方法最容易想到

```java
代码class Solution {
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        if(len == 1) return nums[0];
        fastSort(nums,0,len -1);
        return nums[len - k];
    }
    public void fastSort(int[] nums, int start, int end){
        if(start >= end) return;
        int key = nums[start];
        int left = start, right = end;
        while(left < right){
            while(left < right && nums[right] >= nums[left])
                right--;
            nums[left] = nums[right];
            while(left < right && nums[left] <= nums[right])
                left++;
            nums[right] = nums[left];
        }
        nums[left] = key;
        fastSort(nums,start,left - 1);
        fastSort(nums,right + 1, end);
    }
}
```
时间复杂度：O(nlogn)
空间复杂度：O(1)

### 方法二：堆

穿件一个优先级队列，队列大小为看，依次将数组元素加入队列中，队列中的元素按照升序排列，优先级队列本质上是一个小根堆。如果队列为空直接将元素插入队列中；如果队列已满，则比较堆顶元素与当前元素大小，如果当前元素小于等于堆顶元素，说明当前元素不可能出现在前k大的集合中，直接丢弃，如果当前元素大于堆顶元素，则将堆顶元素删除并将当前元素加入堆中。最后堆顶元素就是第k大的元素

```java
import java.util.Comparator;
import java.util.PriorityQueue;
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 定义一个优先级队列，队列中的数据类型是Integer,表示每个元素
        // TODO: 优先级队列其实一个堆，堆的底层实现是数组，Java默认是小根堆
        // TODO: 优先级队列中按照元素大小升序排列,其实用默认的比较器也可以
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return a - b;
            }
        });
        // 创建大小为k的堆
        for(int num : nums){
            // 如果队列元素个数已达到k个，出现次数低于堆顶次数的直接丢弃
            if(queue.size() == k){
                if(queue.peek() < num){
                    queue.poll();
                    queue.offer(num);
                }
            } else{
                queue.offer(num);
            }
        }
        // 取出堆顶元素就是第k大的元素
        int numKth = queue.poll();
        return numKth;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(k)

