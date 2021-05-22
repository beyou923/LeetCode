class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 遍历行
        for(int col = 0; col < board[0].length; col++){
            int[] count = new int[10];
            for(int i = 0; i < board.length; i++){
                int value = board[i][col] - '0';
                if(value >= 1 && value <= 9){
                    if(count[value] != 0) return false;
                    count[value]++;
                }
            }
        }

        // 遍历列
        for(int row = 0; row < board.length; row++){
            int[] count = new int[10];
            for(int j = 0; j < board[0].length; j++){
                int value = board[row][j] - '0';
                if(value >= 1 && value <= 9){
                    if(count[value] != 0)
                        return false;
                    count[value]++;
                }
            }
        }

        // 遍历子数独
        for(int sign = 0; sign < board.length; sign++){
            int col = (sign % 3) * 3; // 3 x 3子网格起始列下标
            int row = (sign / 3) * 3; // 3 x 3子网格起始行下标
            int[] count = new int[10];
            for(int i = col; i < col + 3; i++){
                for(int j = row; j < row + 3; j++){
                    int value = board[i][j] - '0';
                    if(value >= 1 && value <= 9) {
                        if(count[value] != 0) return false;
                        count[value]++;
                    }
                }
            }
        }
        return true;
    }
}
