public class Solution {
    // 动态规划
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1; // 空树也算是一棵树
        G[1] = 1; // 单节点只能有一棵树
        for(int i = 2; i <= n; i++){
            // 计算以i为根节点的左右子树集合的笛卡尔积
            for(int j = 1; j <= i; j++){
                G[i] += G[j - 1] * G[i -j];
            }
        }
        return G[n];
    }
    // 数学方法：卡特兰数
    public int numTrees1(int n) {
        long C = 1;
        for(int i = 1; i < n; i++){
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int)C;
    }
}

