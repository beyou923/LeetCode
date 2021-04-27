## 题目描述

给定一个二叉树，找出其最大深度。二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

**说明:**叶子节点是指没有子节点的节点。

**示例：**

给定二叉树 [3,9,20,null,null,15,7]，

```shell
    3
   / \
  9  20
    /  \
   15   7
```
返回它的最大深度 3 。
来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/maximum-depth-of-binary-tree](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree?fileGuid=k9tHWWCrCGGThR8p)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

递归计算左右子树的深度，返回较大者加1

## 代码

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        // 分别计算左右子树的深度
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }
}
```
## 复杂度分析

### 时间复杂度

O(n)，其中n为节点个数，每个节点只遍历一遍

### 空间复杂度

O(hight)，hight为树的深度，也就是递归深度。

