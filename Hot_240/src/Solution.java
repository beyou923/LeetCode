public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // 每次与右上角的元素比较
        // TODO: 大于target的元素都在下方，小于target的元素都在左边
        for(int i = 0; i < m; i++){
            for(int j = n - 1; j >= 0;){
                if(matrix[i][j] == target){
                    return true;
                } else if(matrix[i][j] > target){
                    // 舍弃最后一列
                    j--;
                } else{
                    // 跳出当前循环，舍弃最上面一行 
                    break;
                }
            }
        }
        return false;
    }
}

class Solution1 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // TODO: 每次与最小角元素比较，如果target == 左下角元素，直接返回
        // TODO: 如果target > 左下角元素,舍弃当前列（大的元素都在右边）
        // TODO: 如果target < 左下角元素,舍弃当前行（大的元素都在上边）
        for(int i = m -1; i >= 0; i--){
            for(int j = 0; j < n;){
                if(matrix[i][j] == target){
                    return true;
                } else if(matrix[i][j] > target){
                    break;
                }else{
                    j++;
                }
            }
        }
        return false;
    }
}
