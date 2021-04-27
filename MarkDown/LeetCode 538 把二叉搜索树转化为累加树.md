## 题目

给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。

提醒一下，二叉搜索树满足下列约束条件：

* 节点的左子树仅包含键 小于 节点键的节点。
* 节点的右子树仅包含键 大于 节点键的节点。
* 左右子树也必须是二叉搜索树。

**注意**：本题和 1038:[https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/](https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/?fileGuid=YPtdQhy9WCVTWY3D)相同

**示例 1**：

![图片](https://uploader.shimo.im/f/dgSA3UDJuNWv2crC.png!thumbnail?fileGuid=YPtdQhy9WCVTWY3D)

>输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
>输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]

**示例 2**：

>输入：root = [0,null,1]
>输出：[1,null,1]

**示例 3**：

>输入：root = [1,0,2]
>输出：[3,3,2]

**示例 4**：

>输入：root = [3,2,4,1]
>输出：[7,9,4,10]

**提示**：

* 树中的节点数介于 0 和 10^4 之间。
* 每个节点的值介于 -10^4 和 10^4 之间。
* 树中的所有值 互不相同 。
* 给定的树为二叉搜索树。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/convert-bst-to-greater-tree](https://leetcode-cn.com/problems/convert-bst-to-greater-tree?fileGuid=YPtdQhy9WCVTWY3D)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：逆中序遍历

需要一个pre指针记录当前遍历节点cur的前一个节点，这样才方便做累加。

```java
class Solution {
    int pre = 0;
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }
    
    public void dfs(TreeNode root) {
        if(root == null) return;
        
        dfs(root.right);
        root.val += pre;
        pre = root.val;
        System.out.println(root.val);
        dfs(root.left);
        }
 }
```
时间复杂度：O(n)
空间复杂度：O(n)

