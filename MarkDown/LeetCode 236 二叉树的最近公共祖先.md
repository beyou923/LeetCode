## 题目

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

**示例 1**：

![图片](https://uploader.shimo.im/f/2vnODfMpdLCeOmlf.png!thumbnail?fileGuid=QKCQHYpGk9cwCPqW)

>输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
>输出：3
>解释：节点 5 和节点 1 的最近公共祖先是节点 3 。

**示例 2**：

![图片](https://uploader.shimo.im/f/bZdKX58r2a4fTcPD.png!thumbnail?fileGuid=QKCQHYpGk9cwCPqW)

>输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
>输出：5
>解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。

**示例 3**：

>输入：root = [1,2], p = 1, q = 2
>输出：1

**提示**：

* 树中节点数目在范围 [2, 105] 内。
* -10^9 <= Node.val <= 10^9
* 所有 Node.val 互不相同 。
* p != q
* p 和 q 均存在于给定的二叉树中。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree?fileGuid=QKCQHYpGk9cwCPqW)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：后序遍历

祖先结点的定义： 若结点p 在节点root 的左（右）子树中，或 p = root ，则称 root 是 p 的祖先。

![图片](https://uploader.shimo.im/f/lkHgoFJCI7ITRqMY.png!thumbnail?fileGuid=QKCQHYpGk9cwCPqW)

最近公共祖先的定义： 设节点 root 为节点 p, q的某公共祖先，若其左子节点 root.left 和右子节点root.right 都不是 p,q 的公共祖先，则称root 是 “最近的公共祖先” 。根据以上定义，若 root 是 p,q 的 最近公共祖先 ，则只可能为以下情况之一：

* p 和 q 在root 的子树中，且分列root 的 异侧（即分别在左、右子树中）；
* p=root ，且 q 在 root 的左或右子树中；
* q=root ，且 p 在 root 的左或右子树中；

![图片](https://uploader.shimo.im/f/ilLVLXXS7M8kNvUx.png!thumbnail?fileGuid=QKCQHYpGk9cwCPqW)

考虑通过递归对二叉树进行后序遍历，当遇到节点 p 或 q 时返回。从底至顶回溯，当节点 p, q 在节点root 的异侧时，节点 root 即为最近公共祖先，则向上返回 root 。

**递归解析**：

1. 终止条件：
    * 当越过叶节点，则直接返回 null ；
    * 当 root 等于 p,q ，则直接返回 root ；
2. 递推工作：
    * 开启递归左子节点，返回值记为 left ；
    * 开启递归右子节点，返回值记为right ；
3. 返回值： 根据 left 和 right ，可展开为四种情况；
    * 当left 和 right 同时为空 ：说明root 的左 / 右子树中都不包含p,q ，返回 null ；
    * 当 left 和 right 同时不为空 ：说明p,q 分列在 root 的 异侧 （分别在 左 / 右子树），因此 root 为最近公共祖先，返回root ；
    * 当 left 为空 ，right 不为空 ：p,q 都不在 root 的左子树中，直接返回 right 。具体可分为两种情况：
        * p,q 其中一个在 root 的 右子树 中，此时 right 指向 p（假设为 p ）；
        * p,q 两节点都在 root 的 右子树 中，此时的 right 指向 最近公共祖先节点 ；
    * 当 left 不为空 ， right 为空 ：与情况 3. 同理；
```java
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(){}
    public TreeNode(int val){
        this.val = val;
    }
}
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }
}
```
**时间复杂度 O(N)**： 其中 N 为二叉树节点数；最差情况下，需要递归遍历树的所有节点。
**空间复杂度 O(N)**： 最差情况下，递归深度达到 N ，系统使用 O(N) 大小的额外空间。

