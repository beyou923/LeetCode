public class Solution {
    // 2次旋转
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length < 1 || matrix[0].length < 1) return;
        int n = matrix.length;
        // 水平翻转
        for(int i = 0; i < n / 2; i++){
            for(int j = 0; j < n; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = tmp;
            }
        }
        // 对角线翻转
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){ // 这里条件一定是 j < i 不是 j < n
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    // 辅助数组
    public void rotate1(int[][] matrix) {
        if(matrix == null || matrix.length < 1 || matrix[0].length < 1) return;
        int row = matrix.length, col = matrix[0].length;
        int[][] matrixNew = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                matrixNew[j][col - 1 - i] = matrix[i][j];
            }
        }

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++)
                matrix[i][j] = matrixNew[i][j];
        }
    }
}
