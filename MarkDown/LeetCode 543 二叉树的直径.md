## 题目描述

给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。

**示例 :**给定二叉树

```shell
          1
         / \
        2   3
       / \     
      4   5    
```
返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
**注意**：两结点之间的路径长度是以它们之间边的数目表示。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/diameter-of-binary-tree](https://leetcode-cn.com/problems/diameter-of-binary-tree?fileGuid=KgGDqj8QXCJhcTkw)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

递归统计左右子树最大深度 left 和right,返回left + right + 1

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
    int deep = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return deep;
    }
    public int depth(TreeNode root) {
        if (root == null) {
            return 0; // 访问到空节点了，返回0
        }
        int left = depth(root.left); // 左儿子为根的子树的深度
        int right = depth(root.right); // 右儿子为根的子树的深度
        if(deep < left + right)
            deep = left + right;
        return Math.max(left, right) + 1; // 返回该节点为根的子树的深度
    }
    public static void main(String[] args){
        Solution s = new Solution();
        TreeNode left = new TreeNode(2,null,null);
        TreeNode right = new TreeNode(3,null,null);
        TreeNode root = new TreeNode(1, left,right);
        int result = s.diameterOfBinaryTree(root);
        System.out.println(result);
    }
}
```
## 复杂度分析

### 时间复杂度:O(n)

### 空间复杂度

O(hight)，hight为树的深度，也就是递归深度。

### 
