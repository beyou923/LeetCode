## 题目描述

给定一个二叉树，检查它是否是镜像对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

```shell
    1
   / \
  2   2
 / \ / \
3  4 4  3
```
但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
```shell
    1
   / \
  2   2
   \   \
   3    3
```
来源：力扣（LeetCode）
链接：[https://leetcode-cn.com/problems/symmetric-tree](https://leetcode-cn.com/problems/symmetric-tree?fileGuid=WGjrq9tgrX8PtwCg)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

递归判断左右子树是否对称。

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
class Solution {
public boolean isSymmetric(TreeNode root) {
if(root == null) return true; // 根节点为空直接返回true
return symmetric(root.left, root.right); // 递归判断左右子树
}
public boolean symmetric(TreeNode t1, TreeNode t2){
if(t1 == null || t2 == null) return t1 == t2;// 2棵子树根节点为空返回true；有一棵不为空返回false
return t1.val == t2.val && symmetric(t1.left,t2.right) && symmetric(t1.right,t2.left);
}
}
```
