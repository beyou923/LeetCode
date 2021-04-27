## 题目

以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

**示例 1**：

>输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
>输出：[[1,6],[8,10],[15,18]]
>解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

**示例 2**：

>输入：intervals = [[1,4],[4,5]]
>输出：[[1,5]]
>解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。

**提示**：

* 1 <= intervals.length <= 10^4
* intervals[i].length == 2
* 0 <= starti <= endi <= 10^4

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/merge-intervals](https://leetcode-cn.com/problems/merge-intervals?fileGuid=VDDQ6jkdvDVpyQrP)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：排序

将intervals数组中的区间按照 区间左端点排序，那么在排完序的列表中，可以合并的区间一定是连续的。如下图所示，标记为蓝色、黄色和绿色的区间分别可以合并成一个大区间，它们在排完序的列表中是连续的：

![图片](https://uploader.shimo.im/f/Qlldb0Qdzjj0ctyz.png!thumbnail?fileGuid=VDDQ6jkdvDVpyQrP)

定义一个数组 merged 存储最终的答案。首先，我们将列表中的区间按照左端点升序排序。然后我们将第一个区间加入 merged 数组中，并按顺序依次考虑之后的每个区间：

* 如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，我们可以直接将这个区间加入数组 merged 的末尾；
* 否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
class Solution {
public int[][] merge(int[][] intervals) {
if(intervals == null || intervals.length < 1) return new int[0][0];
// 对 intervals中的区间按照左断点排序
Arrays.sort(intervals,new Comparator<int[]>(){
public int compare(int[] comparator1, int[] comparator2){
return comparator1[0] - comparator2[0];
}
});
List<int[]> merged = new ArrayList<>();
for(int i = 0; i < intervals.length; i++){
// 记录区间左右端点
int start = intervals[i][0], end = intervals[i][1];
// 直接插入
if(merged.size() == 0 || merged.get(merged.size() - 1)[1] < start){
merged.add(intervals[i]);
} else {
// 保证每一段区间右端点最大
merged.get(merged.size() - 1)[1] = Math.max(end, merged.get(merged.size() - 1)[1]);
}
}
return merged.toArray(new int[merged.size()][2]);
}
}
```
时间复杂度：O(nlogn)
空间复杂度：O(logn)

