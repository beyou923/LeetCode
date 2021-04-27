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

