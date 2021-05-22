import java.util.*;

public class Solution {
}

// 排序法
class Solution1{
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return 1;
        Arrays.sort(nums);
        // max：最长连续子序列  currLen：当前连续子序列的长度 prev：上一个遍历的值
        int max = 1, currLen = 1, prev = nums[0];
        for(int i = 1; i < nums.length; i++){
            // 当前值与上一个值相差1，当前连续字串长度加1，更行上一个值
            if(nums[i] - prev == 1){
                currLen++;
                prev = nums[i];
            } else if(nums[i] == prev){
                // 遇到重复值，继续
                continue;
            }
            max = Math.max(currLen, max);
            // 当前连续子序列结束，开始一个新的连续子序列
            if(nums[i] - prev > 1){
                currLen = 1;
                prev = nums[i];
            }
        }
        return Math.max(max,currLen);
    }
}
// 哈希表
class Solution2{
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for(int num : nums){
            set.add(num);
        }
        int max = 0;
        // TODO: 这里一定是对set遍历，不要对数组遍历，如果对数组遍历数组中可能有重复元素，导致很多重复计算
        for(int num : set){
            // 技巧：如果有比自己小一点的，那自己不查，让小的去查
            if(!set.contains(num - 1)){
                int value = num;
                int currLen = 1;

                while(set.contains(value + 1)){
                    currLen += 1;
                    value += 1;
                }
                max = Math.max(max, currLen);
            }

        }
        return max;
    }
}

// 并查集
class Solution3{
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        // 首次遍历，与邻居结盟
        UnionFind uf = new UnionFind(nums);
        for (int v : nums)
            uf.union(v, v + 1); // uf.union() 结盟

        // 二次遍历，记录领队距离
        int max = 1;
        for (int v : nums)
            max = Math.max(max, uf.find(v) - v + 1); // uf.find() 查找领队
        return max;
    }
}
class UnionFind{
    private Map<Integer, Integer> parent; // 当前节点：根节点
    private int count; // 并查集中节点个数

    public UnionFind(int[] nums){
        parent = new HashMap<>();
        for(int num : nums)
            parent.put(num, num); // 初始时每个节点的根节节点就是其本身
        count = parent.size(); // 不是数组长度！！！因为数组中可能有重复元素
    }
    // 合并操作
    public void union(int p, int q){
        // 合并p q需要先找到它们各自的根节点
        Integer rootP = find(p);
        Integer rootQ = find(q);
        // 判断p q是否在同一个集合中
        if(rootP == rootQ)
            return;
        // 注意判断返回值是否为空
        if(rootP == null || rootQ == null) return;
        // 将p q各自根节点中较大者作为合并后的根
        if(rootP < rootQ){
            parent.put(rootP, rootQ);
        }else{
            parent.put(rootQ,rootP);
        }
        count--;
    }
    // 查找操作 (找根)
    public Integer find(int p){
        // 首先判断要查找的节点是否在并查集中
        if(!parent.containsKey(p))
            return null;
        int root = p; // 假设当前节点就是根节点
        while(root != parent.get(root)){
            // 递归查找root节点的根
            root = parent.get(root);
        }
        // 优化：防止查找路径过长，保存之前查询结果
        while(p != parent.get(p)){
            int curr = p;
            // 以下两句不能颠倒
            p = parent.get(p);
            parent.put(curr,root);
        }
        return root;
    }
}