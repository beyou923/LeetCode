import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    // 滑动窗口 + 单调队列（基于双端队列实现）
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>(); // 双端队列
        // 窗口的左右边界
        int start = 0, end = 0;
        int[] ans = new int[nums.length - k + 1];
        // 滑动窗口
        while (end < nums.length){
            int value = nums[end];
            // 如果当前元素大于队尾元素，那么当前元素就有可能成为滑动窗口的最大值，需要将其移动到尽量靠前的位置
            while (!deque.isEmpty() && value > deque.getLast()){
                deque.pollLast();
            }
            deque.offerLast(value);
            // 窗口已满
            if (end - start + 1 == k){
                // 对头元素就是当前窗口的最大值
                int max = deque.getFirst();
                ans[start] = max;
                // 如果移出窗口的元素就是最大值，需要将对头元素弹出队列
                if (max == nums[start])
                    deque.pollFirst();
                start++; // 左边界向右移动
            }
            end++;
        }

        return ans;
    }
    public static void main(String[] args){
        int[] nums = new int[]{1,-1};
        int k = 1;
        int[] ans = maxSlidingWindow(nums,k);
        for (int v : ans)
            System.out.print(v + " ");
    }
}
