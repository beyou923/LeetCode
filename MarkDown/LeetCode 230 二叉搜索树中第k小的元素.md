## 题目

给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。

示例 1：

![图片](https://uploader.shimo.im/f/FXv9v8UbQ1SX5Qg2.png!thumbnail?fileGuid=vK9QgpCd6vdrRhjJ)

>输入：root = [3,1,4,null,2], k = 1
>输出：1

**示例 2**：

>输入：root = [5,3,6,2,4,null,null,1], k = 3
>输出：3

**提示**：

* 树中的节点数为 n 。
* 1 <= k <= n <= 10^4
* 0 <= Node.val <= 10^4

**进阶**：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst](https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst?fileGuid=vK9QgpCd6vdrRhjJ)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

中序遍历二叉搜索树会得到一个递增序列，设置两个全局变量result和count，分别用于记录第k小的元素和当前遍历到了第几个节点了。

```java
/**
 * Definition for a binary tree node.
 *  */
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
    int result, count;
    public int kthSmallest(TreeNode root, int k) {
        if(root.left != null)
            kthSmallest(root.left, k);
        if(++count == k){
            result = root.val;
        }
        if(root.right != null)
            kthSmallest(root.right,k);
        return result;
    }
}
```
