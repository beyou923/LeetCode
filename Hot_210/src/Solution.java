import java.util.ArrayList;
import java.util.List;

class Solution {
    // 存储有向图
    List<List<Integer>> adjacency;
    // 标记每个节点的状态：0=未搜索，1=搜索中，-1=已完成
    int[] visited;
    // 用数组来模拟栈，下标 n-1 为栈底，0 为栈顶
    int[] result;
    // 判断有向图中是否有环
    boolean valid = true;
    // 栈下标
    int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        adjacency = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            adjacency.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        result = new int[numCourses];
        index = numCourses - 1;
        for (int[] info : prerequisites) {
            adjacency.get(info[1]).add(info[0]);
        }
        // 每次挑选一个「未搜索」的节点，开始进行深度优先搜索
        for (int vertex = 0; vertex < numCourses && valid; ++vertex) {
            if (visited[vertex] == 0) {
                dfs(vertex);
            }
        }
        if (!valid) {
            return new int[0];
        }
        // 如果没有环，那么就有拓扑排序
        return result;
    }

    public void dfs(int vertex) {
        // 将节点标记为「搜索中」
        visited[vertex] = 1;
        // 搜索其相邻节点
        // 只要发现有环，立刻停止搜索
        for (int v: adjacency.get(vertex)) {
            // 如果「未搜索」那么搜索相邻节点
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            }
            // 如果「搜索中」说明找到了环
            else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        // 将节点标记为「已完成」
        visited[vertex] = -1;
        // 将节点入栈
        result[index--] = vertex;
    }
}
