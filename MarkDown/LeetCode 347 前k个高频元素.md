## 题目

给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。

**示例 1**:

>输入: nums = [1,1,1,2,2,3], k = 2
>输出: [1,2]

**示例 2**:

>输入: nums = [1], k = 1
>输出: [1]

**提示**：

* 1 <= nums.length <= 10^5
* k 的取值范围是 [1, 数组中不相同的元素的个数]
* 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的



**进阶**：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/top-k-frequent-elements](https://leetcode-cn.com/problems/top-k-frequent-elements?fileGuid=DwXrDgRtrCdhw9WP)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

本题与[LeetCode 215](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/?fileGuid=DwXrDgRtrCdhw9WP)题基本上一样，都是用堆解决。首先统计每个元素出现的次数，将元素与其出现次数的映射关系存储在一个map中，可以利用堆的思想：建立一个小顶堆，然后遍历map：

* 如果堆的元素个数小于 k，就可以直接插入堆中。
* 如果堆的元素个数等于 k，则检查堆顶与当前出现次数的大小。如果堆顶更大，说明至少有 k 个数字的出现次数比当前值大，故舍弃当前值；否则，就弹出堆顶，并将当前值插入堆中。

遍历完成后，堆中的元素就代表了出现次数前 k 大的值。

```java
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> counter = new HashMap<>();
        // 统计每个元素出现的次数
        for(int num : nums){
            counter.put(num, counter.getOrDefault(num,0) + 1);
        }
        // TODO：用堆实现优先级队列，堆底层实现是数组
        // TODO:队列中元素是数组类型，数组中有2个元素，第一个数组元素表示元素值，第二个数组元素表示元素出现次数
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                // 按照出现次数升序排序
                return a[1] - b[1];
            }
        });
        // 遍历数组元素出现次数map
        for(Map.Entry<Integer, Integer> entry : counter.entrySet()){
            int num = entry.getKey();
            int count = entry.getValue();
            if(queue.size() == k ){
                if(queue.peek()[1] < count){
                    queue.poll();
                    queue.offer(new int[]{num,count});
                }
            }else{
                queue.offer(new int[]{num,count});
            }
        }
        // 将优先级队列中k个元素输出
        int[] result = new int[k];
        for(int i = 0; i < k; i++){
            result[i] = queue.poll()[0];
        }
        return result;
    }
}
```
时间复杂度：O(nlogk)
空间复杂度：O(k)

