## 题目

给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]

![图片](https://uploader.shimo.im/f/mf8HDTGWy7xzh8LX.png!thumbnail?fileGuid=rYtDYjkgrJPhwPrk)

**示例 1**:

>输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
>输出: 6
>解释: 节点 2 和节点 8 的最近公共祖先是 6。

**示例 2:**

>输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
>输出: 2
>解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。

**说明**:

* 所有节点的值都是唯一的。
* p、q 为不同节点且均存在于给定的二叉搜索树中。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree?fileGuid=rYtDYjkgrJPhwPrk)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 方法一：循环

这题让求二叉搜索树的最近公共祖先，而二叉搜索树的特点就是 左子树的所有节点都小于当前节点，右子树的所有节点都大于当前节点，并且每棵子树都具有上述特点，所以这题就好办了，从更节点开始遍历

* 如果两个节点值都小于根节点，说明他们都在根节点的左子树上，我们往左子树上找
* 如果两个节点值都大于根节点，说明他们都在根节点的右子树上，我们往右子树上找
* 如果一个节点值大于根节点，一个节点值小于根节点，说明他们他们一个在根节点的左子树上一个在根节点的右子树上，那么根节点就是他们的最近公共祖先节点。

画个图看一下，比如要找0和5的最近公共祖先节点，如下图所示

![图片](https://uploader.shimo.im/f/SNhJpqwrdnZliwlY.png!thumbnail?fileGuid=rYtDYjkgrJPhwPrk)

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //如果根节点和p,q的差相乘是正数，说明这两个差值要么都是正数要么都是负数，也就是说
        //他们肯定都位于根节点的同一侧，就继续往下找
        while((root.val - p.val) * (root.val - q.val) > 0){
            root = root.val > p.val ? root.left : root.right;
        }
         //如果相乘的结果是负数，说明p和q位于根节点的两侧；如果等于0，说明至少有一个就是根节点
        return root;
    }
}
```
**时间复杂度**：O(n)
**空间复杂度**：O(1)

### 方法二：递归

将上面的方法改成递归实现

```java
public class Solution {
    // 递归实现
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((root.val - p.val) * (root.val - q.val) <= 0)
            return root;
        return lowestCommonAncestor(root.val > p.val ? root.left : root.right, p, q);
    }
}
```
