import java.util.*;

class Solution {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(k > nums.length) return null;
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2){
                return o2 - o1;
            }
        });
        int[] res = new int[nums.length];
        int start = 0 ,end = 0;
        while(end < nums.length){
            queue.add(nums[end]);
            if(end - start + 1 == k){
                int max = queue.peek();
                res[start] = max;
                queue.remove(nums[start]);
                start++;
            }
            end++;
        }
        int[] ans = new int[start];
        for (int i = 0; i < start; i++)
            ans[i] = res[i];
        return ans;
    }
    public static void main(String[] args){
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] ans = maxSlidingWindow(nums,k);
        for (int num : ans)
            System.out.println(num);
    }
}