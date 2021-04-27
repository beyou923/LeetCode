## 题目描述

给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

**示例 1:**

```shell
输入: 
      Tree 1                     Tree 2                 
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
输出: 
合并后的树:
     3
    / \
   4   5
  / \   \ 
5   4   7
```
**注意**: 合并必须从两个树的根节点开始。
来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/merge-two-binary-trees](https://leetcode-cn.com/problems/merge-two-binary-trees?fileGuid=d8V8HxVj9hvJpV9R)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

可以使用深度优先搜索合并两个二叉树。从根节点开始同时遍历两个二叉树，并将对应的节点进行合并。两个二叉树的对应节点可能存在以下三种情况，对于每种情况使用不同的合并方式。

1. 如果两个二叉树的对应节点都为空，则合并后的二叉树的对应节点也为空；
2. 如果两个二叉树的对应节点只有一个为空，则合并后的二叉树的对应节点为其中的非空节点；
3. 如果两个二叉树的对应节点都不为空，则合并后的二叉树的对应节点的值为两个二叉树的对应节点的值之和，此时需要显性合并两个节点。

对一个节点进行合并之后，还要对该节点的左右子树分别进行合并。这是一个递归的过程

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
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null)  return root2;
        if(root2 == null) return root1;
        TreeNode node = new TreeNode(root1.val + root2.val);
        node.left = mergeTrees(root1.left, root2.left);
        node.right = mergeTrees(root1.right, root2.right);
        return node;
    }
}
```
## 复杂度分析

### 时间复杂度:O(min(m,n))

其中,m和n分别是两棵树的节点个数

### 空间复杂度:O(min(m,n))

其中,m和n分别是两棵树的节点个数

