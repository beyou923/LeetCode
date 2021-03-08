import java.util.ArrayList;
import java.util.List;
/*
*
* */
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        if (numRows == 0) {
            return triangle;
        }
        // 添加第一行
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);
        // 从第二行开始
        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> preRow = triangle.get(rowNum - 1);
            // 每行的第一个元素总是1
            row.add(1);
            // 从每行第二个元素开始填写
            for (int j = 1; j < rowNum; j++) {
                // 每行除首尾元素外，每个元素大小等于前一行对应元素与前一行前一列元素之和
                row.add(preRow.get(j-1) + preRow.get(j));
            }
            // 每行最后一个元素总是1
            row.add(1);
            // 将每行元素添加到二维数组
            triangle.add(row);
        }
        return triangle;
    }
    public static void main(String args[]) {
        Solution solution = new Solution();
        List<List<Integer>> ans = new ArrayList<>();
        ans = solution.generate(5);
        for(int i = 0;i < ans.size();i++){
            System.out.println(ans.get(i));
        }
    }
}
