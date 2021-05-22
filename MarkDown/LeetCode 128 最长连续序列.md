## 题目

给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

**进阶**：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？

**示例 1**：

>输入：nums = [100,4,200,1,3,2]
>输出：4
>解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。

**示例 2**：

>输入：nums = [0,3,7,2,5,8,4,6,0,1]
>输出：9

**提示**：

* 0 <= nums.length <= 10^4
* -10^9 <= nums[i] <= 10^9

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/longest-consecutive-sequence](https://leetcode-cn.com/problems/longest-consecutive-sequence?fileGuid=jhktdryWkkkCTJkv)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 方法一：排序法

先对原数组排序，排序后的数组如果存在连续子数组必定是连续的（中间可能出现重复值），定义三个变量分别是`max`、`prev`和`currLen`这三个变量分别是记录最长连续字串的值、上一个遍历到的值和当前连续字串的长度。当前后两个元素相差1，将`currLen`自增1，如果出现重复元素，继续遍历，在过程中将max和currLen中较大值赋值给max。如果出现前后两个元素差值大于1，说明当前连续字串结束了，开始一个字串，将currLen的值改成1同时将prev修改为当前元素，重复上述过程，最后返回max和currLen中较大值赋值

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return 1; 
        Arrays.sort(nums);
        // max：最长连续子序列  currLen：当前连续子序列的长度 prev：上一个遍历的值
        int max = 1, currLen = 1, prev = nums[0];
        for(int i = 1; i < nums.length; i++){
            // 当前值与上一个值相差1，当前连续字串长度加1，更行上一个值
            if(nums[i] - prev == 1){
                currLen++;
                prev = nums[i];
            } else if(nums[i] == prev){
                // 遇到重复值，继续
                continue;
            } 
            max = Math.max(currLen, max);
            // 当前连续子序列结束，开始一个新的连续子序列
            if(nums[i] - prev > 1){
                currLen = 1;
                prev = nums[i];
            }
        }
        return Math.max(max,currLen);
    }
}
```
时间复杂度：O(nlogn)
空间复杂度：O(1)

### 方法二：集合

利用 O(1) 时间复杂度「查询是否有下一个」

优化：如果有比自己小一点的，那自己不查，让小的去查，并且for循环一定是对set集合遍历，不要对数组遍历，如果对数组遍历数组中可能有重复元素，导致很多重复计算

```java
class Solution{
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
```
时间复杂度：O(n)
空间复杂度：O(n)

### 方法三：并查集

* 初始：所有元素各自为战，其根节点为其本身
* 首次遍历：所有元素 x 向各自邻居 x + 1合并，并「以大者为根节点」
    * 若有邻居，才合并成功
    * 根节点，即 区间右边界
    * 不只是元素 x 与邻居 x + 1 合并，而是整个 x 所在队伍与整个 x + 1 所在队伍合并
        * 如 [1, 2, 3] 与 [4, 5] 两个队伍合并
* 二次遍历：记录所有人与其根节点距离
    * 距离，即 区间右边界 - 当前元素 + 1

代码如下

```java
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
```
时间复杂度：O(n)
空间复杂度：O(n)

