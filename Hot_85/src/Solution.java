import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int max = 0;
        int[][] left = new int[m][n];
        // step 1：统计位置[i,j]为1是其左边（包括位置[i,j]连续1的个数）
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '1'){
                    left[i][j] = (j == 0 ? 0 : left[i][j-1]) + 1;
                }
            }
        }
        // step 2：选择以[i,j]为右下角的矩形的最大宽度
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '0')
                    continue;

                int width = left[i][j];
                int area = width;
                for(int k = i - 1; k >= 0; k--){
                    width = Math.min(width, left[k][j]);
                    area = Math.max(area, width * (i - k + 1));
                }
                max = Math.max(area, max);
            }
        }
        return max;
    }
}

// 单调队列
class Solution1 {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int max = 0;
        int[][] left = new int[m][n];
        // step 1：统计位置[i,j]为1是其左边（包括位置[i,j]连续1的个数）
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '1'){
                    left[i][j] = (j == 0 ? 0 : left[i][j-1]) + 1;
                }
            }
        }
        // 对于每一列用LeetCode 84的方法
        for(int j = 0; j < n; j++){
            int[] start = new int[m];
            int[] end = new int[m];
            Deque<Integer> stack = new LinkedList<>();
            for(int i = 0; i < m ;i++){
                while(!stack.isEmpty() && left[stack.peek()][j] >= left[i][j])
                    stack.pop();

                start[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear(); // 这一句不可或缺
            for(int i = m -1; i >= 0; i--){
                while(!stack.isEmpty() && left[stack.peek()][j] >= left[i][j])
                    stack.pop();

                end[i] = stack.isEmpty() ? m : stack.peek();
                stack.push(i);
            }

            for(int i = 0; i < m; i++){
                int h = end[i] - start[i] - 1;
                max = Math.max(max, h * left[i][j]);
            }
        }
        return max;
    }
}
