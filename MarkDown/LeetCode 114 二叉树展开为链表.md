## 题目

给你二叉树的根结点 root ，请你将它展开为一个单链表：

* 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
* 展开后的单链表应该与二叉树 先序遍历 顺序相同。

**示例 1**：

![图片](https://uploader.shimo.im/f/EtvV2RTkq4kpNa2x.png!thumbnail?fileGuid=HKGqT3K6ccvRkDv6)

>输入：root = [1,2,5,3,4,null,6]
>输出：[1,null,2,null,3,null,4,null,5,null,6]

**示例 2**：

>输入：root = []
>输出：[]

**示例 3**：

>输入：root = [0]
>输出：[0]

**提示**：

* 树中结点数在范围 [0, 2000] 内
* -100 <= Node.val <= 100

**进阶**：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list](https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list?fileGuid=HKGqT3K6ccvRkDv6)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：递归

递归地将根节点root的左右子树展开为两个链表，并记录左边链表的尾和右边链表的头，将root节点右指针指向左边链表的头，左边链表的尾部指向右边链表的头，最后将root节点左指针置空

```java
public class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;
        flatten(root.right);
        flatten(root.left);
        TreeNode curr = root.left;
        if(curr == null) return;
        // 找到左边链表的尾
        while(curr.right != null){
            curr = curr.right;
        }
        // root.right是右边链表的头
        curr.right = root.right;
        // root.left 是左边链表的头
        root.right = root.left;
        root.left = null;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(n)

