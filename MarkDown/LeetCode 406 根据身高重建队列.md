## 题目

假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。

请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。

**示例 1**：

>输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
>输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
>解释：
>编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
>编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
>编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
>编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
>编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
>编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
>因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。

**示例 2**：

>输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
>输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]

**提示**：

* 1 <= people.length <= 2000
* 0 <= hi <= 106
* 0 <= ki < people.length
* 题目数据确保队列可以被重建

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/queue-reconstruction-by-height](https://leetcode-cn.com/problems/queue-reconstruction-by-height?fileGuid=jXyxDCrW3hvjxJhh)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：贪心算法

本题有两个维度，h和k，看到这种题目一定要想如何确定一个维度，然后在按照另一个维度重新排列。如果按照k来从小到大排序，排完之后，会发现k的排列并不符合条件，身高也不符合条件，两个维度哪一个都没确定下来。那么按照身高h来排序呢，身高一定是从大到小排（身高相同的话则k小的站前面），让高个子在前面。那么只需要按照k为下标重新插入队列就可以了，为什么呢？以图中{5,2} 为例：

![图片](https://uploader.shimo.im/f/pc7D3zjz1GGv0wwH.png!thumbnail?fileGuid=jXyxDCrW3hvjxJhh)

按照身高排序之后，优先按身高高的people的k来插入，后序插入节点也不会影响前面已经插入的节点，最终按照k的规则完成了队列。

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
public class Solution {
public int[][] reconstructQueue(int[][] people) {
Arrays.sort(people, new Comparator<int[]>(){
public int compare(int[] obj1, int[] obj2){
if(obj1[0] != obj2[0]){
// TODO: 降序就是第二个元素减去第一个元素
return obj2[0] - obj1[0];
} else{
// TODO: 升序就是第一个元素减去第二个元素
return obj1[1] - obj2[1];
}
}
});
List<int[]> queue = new ArrayList<>();
for(int[] person : people){
// 在list的指定索引位置插入元素，如果该位置已有元素，则该位置之后的元素统统后移一个位置
// TODO: list的底层实现是链表，插入很高效
queue.add(person[1],person);
}
return queue.toArray(new int[queue.size()][]);
}
}
```
时间复杂度：O(n^2)
空间复杂度：O(n)

