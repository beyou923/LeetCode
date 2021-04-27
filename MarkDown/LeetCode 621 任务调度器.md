## 题目

给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。

然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。

你需要计算完成所有任务所需要的 最短时间 。

**示例 1**：

>输入：tasks = ["A","A","A","B","B","B"], n = 2
>输出：8
>解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
>在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。

**示例 2**：

>输入：tasks = ["A","A","A","B","B","B"], n = 0
>输出：6
>解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
>["A","A","A","B","B","B"]
>["A","B","A","B","A","B"]
>["B","B","B","A","A","A"]
>...
>诸如此类

**示例 3**：

>输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
>输出：16
>解释：一种可能的解决方案是：
>A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A



**提示**：

* 1 <= task.length <= 10^4
* tasks[i] 是大写英文字母
* n 的取值范围为 [0, 100]

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/task-scheduler](https://leetcode-cn.com/problems/task-scheduler?fileGuid=WdRCWDWQ8vvgKjTq)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：优先级队列

统计每个任务的个数，将任务及其数量记录在一个数组times中，由于任务只用一个大小字母表示，所以没有必要用map，建一个包含26个元素的数组即可，任务的SACII码减去'A'的值作为任务的索引

创建一个优先级队列`priorityQueue`，队列中元素类型为数组类型，数组一共包含2个元素，数组第一个元素和第二个元素分别表示times中任务下标和该任务的个数。将每个任务插入优先级队列中，不同任务间按照任务数降序排序，相同任务数的任务间按照升序排序，也就是创建了一个大顶堆，堆顶的任务总是数量最多的

维护一个队列`lastExecTask`，队列大小为n+1，表示最近执行的n+1个任务，每次从优先级队列中取出堆顶元素执行（也就是加入`lastExecTask`）并将该任务的数量减1，同时最短执行周期加1；如果优先级队列为空，表示没有合适任务可以执行，向`lastExecTask`中加入一个null，表示所有任务处于冷却期或者，同时最短执行时间加1.当`lastExecTask`个数等于n +1时，最终进入队列的任务，已经过了冷却时间，可以再次执行，从`lastExecTask`中弹出该任务，如果该任务数量不为零，将该任务加入优先级队列

重复上述过程，直到待执行的任务数为0

```java
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
public class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;
        char[] times = new char[26];
        int minTime = 0;//
        // 统计每个任务的数量
        for(char ch : tasks){
            times[ch - 'A']++;
        }
        // 创建优先级队列，队列中存储数组类型：数组第一个元素是任务名；数组第二个元素是任务个数
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]){
                    // 不同任务间按照任务数降序排列
                    return o2[1] - o1[1];
                }
                // 相同任务数的任务之间按照任务名字符升序排列
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < times.length; i++){
            if (times[i] > 0)
                priorityQueue.add(new int[]{i,times[i]});
        }
        int N = n + 1;
        // 保存最近N个执行过的任务，空任务用null表示 相当于一个桶
        LinkedList<int[]> lastExecTask = new LinkedList<>();
        int remains = tasks.length; //还有remains个任务没有执行
        while (remains > 0){
            // 桶满了
            if (lastExecTask.size() == N){
                int[] taskOld = lastExecTask.poll();
                // 桶中的任务可能只null （CPU在空等）
                if (taskOld != null && taskOld[1] > 0){
                    priorityQueue.add(taskOld); // 再次将最早被执行的任务插入堆中
                }
            }
            if (!priorityQueue.isEmpty()){
                // 桶没有满，直接从堆中取出堆顶任务执行
                int[] task = priorityQueue.poll();
                task[1]--;//该任务数待执行次数减1
                lastExecTask.add(task);
                remains--;// 待执行的任务数减1
            } else {
                // 没有可以执行的任务，让CPU空闲
                lastExecTask.add(null);
            }
            minTime++; // 最短完成时间加1
        }
        return minTime;
    }
}
```

