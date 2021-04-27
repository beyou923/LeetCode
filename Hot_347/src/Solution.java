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
