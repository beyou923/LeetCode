class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0, right = n -1;
        int up = 0, down = n -1;
        int count = 1;
        while(left <= right && up <= down){
            // 从左到右
            for(int i = left; i <= right; i++)
                matrix[up][i] = count++;
            up++;
            // 从上到小
            for(int i = up; i <= down; i++)
                matrix[i][right] = count++;
            right--;
            // 从右到左
            for(int i = right; i >= left; i--)
                matrix[down][i] = count++;
            down--;
            // 从下到上
            for(int i = down; i >= up; i--)
                matrix[i][left] = count++;
            left++;
        }
        return matrix;
    }
}
