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
