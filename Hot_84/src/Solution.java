import java.util.Stack;

public class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        if (heights.length == 1) return heights[0];
        int len = heights.length;
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[len];
        int[] right = new int[len];
        int max = Integer.MIN_VALUE;
        // step 1 寻找以heights[i] 为高的矩形左边界（左边界前一个位置）
        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear(); // 注意要把栈清空
        // step 2 寻找以heights[i] 为高的矩形右边界（右边界后一个位置）
        for(int i = len - 1; i >= 0; i--){
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }
            right[i] = stack.isEmpty() ? len : stack.peek();
            stack.push(i);
        }
        // step 3: 枚举高h，并计算以h为高的句子矩形面积
        for(int i = 0; i < len; i++){
            max = Math.max(max, (right[i] - left[i] - 1) * heights[i]);
        }
        return max;
    }
}

// 枚举高
class Solution1{
    public int largestRectangleArea(int[] heights) {
        int max = Integer.MIN_VALUE;
        int len = heights.length;
        for(int i = 0; i < len; i++){
            int h = heights[i];
            int left = i, right = i;
            while(left - 1 >= 0 && heights[left - 1] >= h)
                left--;
            while(right + 1 < len && heights[right + 1] >= h)
                right++;

            max = Math.max(max, (right - left + 1) * h);
        }
        return max;
    }
}
// 枚举宽
class Solution2{
    public int largestRectangleArea(int[] heights) {
        int max = Integer.MIN_VALUE;
        for(int left = 0; left < heights.length; left++){
            int minHeight = Integer.MAX_VALUE;
            for(int right = left; right < heights.length; right++){
                minHeight = Math.min(minHeight, heights[right]);
                max = Math.max(max, (right - left + 1) * minHeight);
            }
        }
        return max;
    }
}