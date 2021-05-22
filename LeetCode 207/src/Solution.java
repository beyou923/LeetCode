import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        int[] visited = new int[numCourses];
        // 创建邻接矩阵
        for(int i = 0; i < numCourses; i++){
            adjacency.add(new ArrayList<Integer>());
        }
        // 填充邻接矩阵
        for(int[] info : prerequisites){
            adjacency.get(info[1]).add(info[0]);
        }
        // 以每个顶点为起点开始深度优先搜索
        for(int vertex = 0; vertex < numCourses; vertex++){
            // 存在环，直接退出
            if(!dfs(adjacency, vertex, visited))
                return false;
        }
        return true;
    }

    /**
     *
     * @param adjacency :邻接矩阵
     * @param vertex: 顶点
     * @param visited: 是否被访问过 0:没有被访问过/
     *                            1:被
     * @return :
     */
    public boolean dfs(List<List<Integer>> adjacency , int vertex, int[] visited){
        // 已访问过
        if(visited[vertex] == 1)  return false;
        // 已被从其他顶点出发的dfs遍历过，且不存在环
        if(visited[vertex] == -1) return true;
        // 置已访问标记
        visited[vertex] = 1;
        for(int i : adjacency.get(vertex)){
            if(!dfs(adjacency, i, visited))
                return false;
        }
        // 回溯
        visited[vertex] = -1;
        return true;
    }
}