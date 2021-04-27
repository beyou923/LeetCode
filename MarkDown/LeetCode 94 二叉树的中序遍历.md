## 题目

给定一个二叉树的根节点 root ，返回它的 中序 遍历。

**示例 1**：

![图片](https://uploader.shimo.im/f/CGTkunrmMudswwgL.png!thumbnail?fileGuid=Vw69cDKQ8kvJ8vrC)

>输入：root = [1,null,2,3]
>输出：[1,3,2]

**示例 2**：

>输入：root = []
>输出：[]

**示例 3**：

>输入：root = [1]
>输出：[1]

**示例 4**：

![图片](https://uploader.shimo.im/f/PZTmEih5PMKdizbg.png!thumbnail?fileGuid=Vw69cDKQ8kvJ8vrC)

>输入：root = [1,2]
>输出：[2,1]

**示例 5**：

![图片](https://uploader.shimo.im/f/wKSNkB4IrVmCeu3h.png!thumbnail?fileGuid=Vw69cDKQ8kvJ8vrC)

>输入：root = [1,null,2]
>输出：[1,2]

**提示**：

* 树中节点数目在范围 [0, 100] 内
* -100 <= Node.val <= 100



**进阶**: 递归算法很简单，你可以通过迭代算法完成吗？

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/binary-tree-inorder-traversal](https://leetcode-cn.com/problems/binary-tree-inorder-traversal?fileGuid=Vw69cDKQ8kvJ8vrC)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 方法一：递归

```java
class Solution {
    List<Integer> result = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) return result;
        midTraversal(root);
        return result;
    }
    public void midTraversal(TreeNode root){
        if(root.left != null) midTraversal(root.left);
        result.add(root.val);
        if(root.right != null) midTraversal(root.right);
    }
}
```
时间复杂度：O(n)
空间复杂度：O(n)

### 方法二：循环模拟递归

方法一的递归函数我们也可以用迭代的方式实现，两种方式是等价的，区别在于递归的时候隐式地维护了一个栈，而我们在迭代的时候需要显式地将这个栈模拟出来，其他都相同，具体实现可以看下面的代码。

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> node = new LinkedList<>();
        if(root == null) return result;
        while(root != null || !node.isEmpty()){
            while(root != null){
                node.push(root);
                root = root.left;
            }
            root = node.pop();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(n)

