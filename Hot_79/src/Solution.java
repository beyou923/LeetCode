public class Solution {
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length < 1 || board[0].length < 1) return false;
        int m = board.length, n = board[0].length;
        boolean[][] visted = new boolean[m][n];
        // 从board中任意一个位置开始匹配word中的字母
        for(int row = 0; row < m; row++){
            for(int col = 0; col < n; col++){
                if(dfs(board,word,0,row,col,visted))
                    return true;
            }
        }
        return false;
    }

    // 深度优先遍历
    public boolean dfs(char[][] board, String word, int startIndex, int row, int col, boolean[][] visted){
        if(startIndex == word.length()){
            return true;
        }
        int m = board.length, n = board[0].length;
        if ( row < 0 || col < 0 || row >= m || col >= n ||
                board[row][col] != word.charAt(startIndex) ||
                visted[row][col] ) {
            return false;
        }
        visted[row][col] = true;
        // 以[row,col]为起点，从上下左右四个方向遍历，只要有一个方向匹配成功即可
        boolean res = dfs(board,word,startIndex + 1, row - 1,col,visted) ||
                dfs(board,word,startIndex + 1, row + 1,col,visted) ||
                dfs(board,word,startIndex + 1,row, col - 1,visted) ||
                dfs(board,word,startIndex + 1,row,col + 1,visted);
        // 回溯
        visted[row][col] = false;
        return res;
    }
}
