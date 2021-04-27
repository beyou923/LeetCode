import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int[] dailyTemperatures(int[] T) {
        if(T == null ) return null;
        if(T.length < 2) return new int[]{0};

        int len = T.length;
        int[] result = new int[len];
        // 单调栈：栈中元素表示T中气温的下标
        // TODO: 从栈底到栈顶对应对应温度依次上升
        Deque<Integer> stack = new LinkedList<Integer>();

        for(int i = 0; i < len; i++){
            int temp = T[i];
            // 将当前温度与栈顶温度比较
            while(!stack.isEmpty() && temp > T[stack.peek()]){
                int prev = stack.pop();
                result[prev] = i - prev;
            }
            stack.push(i);
        }
        return result;
    }
}
