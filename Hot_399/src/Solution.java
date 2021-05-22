import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int nvars = 0;
        Map<String, Integer> variables = new HashMap<String, Integer>();

        int n = equations.size();
        // 统计equations中不同节点有多少个
        for (int i = 0; i < n; i++) {
//            variables.put(equations.get(i).get(0),variables.getOrDefault(equations.get(i).get(0),0) + 1);
            if (!variables.containsKey(equations.get(i).get(0))) {
                variables.put(equations.get(i).get(0), nvars++);
            }
//            variables.put(equations.get(i).get(1),variables.getOrDefault(equations.get(i).get(1),0) +1);
            if (!variables.containsKey(equations.get(i).get(1))) {
                variables.put(equations.get(i).get(1), nvars++);
            }
        }
        int[] parent = new int[nvars];
        double[] weight = new double[nvars];
        Arrays.fill(weight, 1.0);
        // 初始时每个节点的父节点是其本身
        for (int i = 0; i < nvars; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0)), vb = variables.get(equations.get(i).get(1));
            union(parent, weight, va, vb, values[i]);
        }
        int queriesCount = queries.size();
        double[] ret = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            List<String> query = queries.get(i);
            double result = -1.0;
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ia = variables.get(query.get(0)), ib = variables.get(query.get(1));
                int fa = find(parent, weight, ia), fb = find(parent, weight, ib);
                if (fa == fb) {
                    result = weight[ia] / weight[ib];
                }
            }
            ret[i] = result;
        }
        return ret;
    }

    public void union(int[] parent, double[] weight, int x, int y, double val) {
        int rootX = find(parent, weight, x);
        int rootY = find(parent, weight, y);
        parent[rootX] = rootY;
        weight[rootX] = val * weight[y] / weight[x];
    }

    public int find(int[] parent, double[] weight, int x) {
        if (parent[x] != x) {
            int father = find(parent, weight, parent[x]);
            weight[x] = weight[x] * weight[parent[x]];
            parent[x] = father;
        }
        return parent[x];
    }
}
