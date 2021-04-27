## 题目

你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] =$$[a_{i},b_{i}]$$，表示如果要学习课程 ai 则 必须 先学习课程  bi 。例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

**示例 1**：

>输入：numCourses = 2, prerequisites = [[1,0]]
>输出：true
>解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。

**示例 2**：

>输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
>输出：false
>解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。

**提示**：

* 1 <= numCourses <= 10^5
* 0 <= prerequisites.length <= 5000
* prerequisites[i].length == 2
* 0 <= ai, bi < numCourses
* prerequisites[i] 中的所有课程对 互不相同

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/course-schedule](https://leetcode-cn.com/problems/course-schedule?fileGuid=TRdcr6TVQPqqHgYX)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：DFS

通过 DFS 判断图中是否有环。

**算法流程**：

1. 借助一个标志列表 visited，用于判断每个节点 i （课程）的状态：
    * 未被 DFS 访问：i == 0；
    * 已被其他节点启动的 DFS 访问：i == -1；
    * 已被当前节点启动的 DFS 访问：i == 1。
2. 对 numCourses 个节点依次执行 DFS，判断每个节点起步 DFS 是否存在环，若存在环直接返回False。DFS 流程；
    * 终止条件：
        * 当 flag[i] == -1，说明当前访问节点已被其他节点启动的 DFS 访问，无需再重复搜索，直接返回 True。
        * 当 flag[i] == 1，说明在本轮 DFS 搜索中节点 i 被第 2 次访问，即 课程安排图有环 ，直接返回False。
    * 将当前访问节点 i 对应 flag[i] 置 1，即标记其被本轮 DFS 访问过；
    * 递归访问当前节点 i 的所有邻接节点 j，当发现环直接返回 False；
    * 当前节点所有邻接节点已被遍历，并没有发现环，则将当前节点 flag 置为 −1 并返回 True。
3. 若整个图 DFS 结束并未发现环，返回 True。
```java
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
    // 深度优先遍历图 
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
```
时间复杂度：O（V + E）

空间复杂度：O（V + E）

其中，V和E分别是顶点数量和边的数量

