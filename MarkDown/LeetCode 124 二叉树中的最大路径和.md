## 题目

路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。路径和 是路径中各节点值的总和。给你一个二叉树的根节点 root ，返回其 最大路径和 。

**示例 1**：

![图片](https://uploader.shimo.im/f/yltgLNaOt1POWlua.png!thumbnail?fileGuid=QgtwkDTPWtghvyc6)

>输入：root = [1,2,3]
>输出：6
>解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6

**示例 2**：

![图片](https://uploader.shimo.im/f/RumiVBddoPAXxiuH.png!thumbnail?fileGuid=QgtwkDTPWtghvyc6)

>输入：root = [-10,9,20,null,null,15,7]
>输出：42
>解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42

**提示**：

* 树中节点数目范围是 [1, 3 * 10^4]
* -1000 <= Node.val <= 1000

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/binary-tree-maximum-path-sum](https://leetcode-cn.com/problems/binary-tree-maximum-path-sum?fileGuid=QgtwkDTPWtghvyc6)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：DFS

二叉树 abc，a 是根结点（递归中的 root），bc 是左右子结点（代表其递归后的最优解）。最大的路径，可能的路径情况：

>a
>/ \
>b   c
1. b + a + c
2. b + a + a 的父结点
3. a + c + a 的父结点。

其中情况 1，表示如果不联络父结点的情况，或本身是根结点的情况。这种情况是没法递归的，但是结果有可能是全局最大路径和。

情况 2 和 3，递归时计算 a+b 和 a+c，选择一个更优的方案返回，也就是上面说的递归后的最优解。

另外结点有可能是负值，最大和肯定就要想办法舍弃负值（max(0,x)。但是上面 3 种情况，无论哪种，a 作为联络点，都不能够舍弃。

代码中使用 max来记录全局最大路径和。return root.val + Math.max(left, right)是情况 2 和 3；max是情况 1。所要做的就是递归，递归时记录好全局最大和，返回联络最大和。

```java
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(){}
    public TreeNode(int val){
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class Solution {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        dfs(root);
        return max;
    }
    // 返回经过root的单边分支最大和， 即Math.max(root, root + left, root + right)
    public int dfs(TreeNode root){
        if(root == null) return 0;
        // 计算左边分支最大值，左边分支如果为负数还不如不选择
        int left = Math.max(0,dfs(root.left));
        // 计算右边分支最大值，右边分支如果为负数还不如不选择
        int right = Math.max(0,dfs(root.right));
        // left->root->right 作为路径与已经计算过历史最大值做比较
        max = Math.max(max, root.val + left + right);
        // 返回经过root的单边最大分支给当前root的父节点计算使用
        return root.val + Math.max(left, right);
    }
}
```
**时间复杂度**：O(n)
**空间复杂度**：O(n)

