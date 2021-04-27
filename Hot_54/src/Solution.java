import java.util.ArrayList;
import java.util.List;
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return result;
        int row = matrix.length;
        int col = matrix[0].length;
        int start = 0; // 每一轮移动的起点 
        while(row > 2 * start && col > 2 * start){
            int endX = col - start -1;
            int endY = row - start -1;
            // 从左往右移动
            if(start <= endX){
                for(int i = start; i <= endX; i++)
                    result.add(matrix[start][i]);
            }
            // 从上到下移动
            if(endY > start ){
                for(int i = start + 1; i <= endY; i++)
                    result.add(matrix[i][endX]);
            }
            //  从右到左移动
            if(endX > start && endY > start){
                for(int i = endX - 1; i >= start; i--)
                    result.add(matrix[endY][i]);
            }
            // 从下到上移动
            if(endX > start && endY - 1 > start){
                for(int i = endY - 1; i > start; i--)
                    result.add(matrix[i][start]);
            }

            start++;
        }
        return result;
    }
}
