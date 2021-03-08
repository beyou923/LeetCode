import java.util.Arrays;
import java.util.List;

/*
*   给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是
*   下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
*   例如，给定三角形：
*       [
            [2],
            [3,4],
            [6,5,7],
            [4,1,8,3]
        ]
*   自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
* 链接：https://leetcode-cn.com/problems/triangle
* */
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size(); // 最后一行元素个数
        if (n == 0)
            return 0;

        int dp[][] = new int[n][n];
        // 将二维数组所有元素赋值为 0
        for (int i = 0; i < n; ++i)
            Arrays.fill(dp[i],0);

        // 初始化dp 数组最后一行
        for (int i = 0; i < n; i++) {
            // 将三角形最后一行复制给dp数组最后一行
            dp[n-1][i] = triangle.get(n-1).get(i);
        }

        // 从三角形倒数第二行开始遍历，填写dp 数组
        for (int i = n-2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // 选择 较小的状态与当前值相加
                dp[i][j] = Math.min(dp[i+1][j],dp[i+1][j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];//dp[0][0]就是最优解
    }
}
