## 题目

给定一个二叉树，判断其是否是一个有效的二叉搜索树。假设一个二叉搜索树具有如下特征：

* 节点的左子树只包含小于当前节点的数。
* 节点的右子树只包含大于当前节点的数。
* 所有左子树和右子树自身必须也是二叉搜索树。

**示例 1**:

>输入:
>2
>/ \
>1   3
>输出: true

**示例 2**:

>输入:
>5
>/ \
>1   4
>/ \
>3   6
>输出: false
>解释: 输入为: [5,1,4,null,null,3,6]。
>根节点的值为 5 ，但是其右子节点值为 4 。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/validate-binary-search-tree](https://leetcode-cn.com/problems/validate-binary-search-tree?fileGuid=JvJJcKjPkhXH6cW6)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

二叉搜索树的中序遍历是一个递增序列，可以用中序遍历二叉树，如果上一次遍历的节点值大于等于本次遍历到的节点值，那么肯定不是二叉搜索树，否则继续递归

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
// 这里一定要用long型，int可能会溢出
long pre = Long.MIN_VALUE;
public boolean isValidBST(TreeNode root) {
if(root == null ) return true;
boolean left = isValidBST(root.left);
if(root.val <= pre)
return false;
pre = root.val;
boolean right = isValidBST(root.right);
return left && right ;
}
}
```
时间复杂度：O(n)
空间复杂度：O(n)

