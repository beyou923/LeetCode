## 题目描述

翻转一棵二叉树。

**示例**：

```shell
输入：
     4
   /   \
  2     7
 / \   / \
1   3 6   9
输出：
     4
   /   \
  7     2
 / \   / \
9   6 3   1
```
**备注:**
这个问题是受到 Max Howell 的 原问题 启发的 ：

>谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/invert-binary-tree](https://leetcode-cn.com/problems/invert-binary-tree?fileGuid=YK89WC8X3DRddtqC)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

先序遍历递归地交换左右子树

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
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        // 递归交换左右子树
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
```
## 复杂度分享

### 时间复杂度

>O(n)，n代表节点个数，每个节点遍历一遍
### 空间复杂度

>最坏情况下为O(n)，也就是一条链；在平均情况下，二叉树的高度与节点个数为对数关系，即 O(log n)。
