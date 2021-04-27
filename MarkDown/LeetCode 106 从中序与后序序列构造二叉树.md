## 题目

根据一棵树的中序遍历与后序遍历构造二叉树。

注意:你可以假设树中没有重复的元素。

例如，给出

>中序遍历 inorder = [9,3,15,20,7]
>后序遍历 postorder = [9,15,7,20,3]

返回如下的二叉树：

>3
>/ \
>9  20
>/  \
>15   7

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal](https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal?fileGuid=kkxjP6JDVX6jJg8y)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：递归

本题思路和[LeetCode 105](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/?fileGuid=kkxjP6JDVX6jJg8y)一样，都是先确定根节点，再根据根节点在中序序列中的位置，确定左右子树中包括哪些节点，再用同样的方法递归构建左右子树。区别在于后续遍历序列中最后一个节点是根节点。

```java
class Solution {
    Map<Integer,Integer> index;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length < 1) return null;
        int len = preorder.length;
        index = new HashMap<>();
        for(int i = 0; i < len; i++){
            index.put(inorder[i],i);
        }
        return builder(preorder,0,len -1,inorder, 0, len -1);
    }
    public TreeNode builder(int[] preorder, int pStart,int pEnd, int[] inorder, int iStart,int iEnd){
        if(pStart > pEnd) 
            return null;
        // 前序遍历中的第一个节点就是根节点
        int preRoot = pStart;
        // 在中序遍历中定位根节点
        int iRoot = index.get(preorder[preRoot]);
        // 创建根节点
        TreeNode root = new TreeNode(preorder[preRoot]);
       // 得到左子树中的节点数目
       int sizeLeftSub = iRoot - iStart;
       // 递归地构造左子树，并连接到根节点
       // 先序遍历中「左边界+1, 左边界+左子树节点数目」个元素就对应了
       // 中序遍历中「左边界，根节点定位-1」的元素
        root.left = builder(preorder, pStart + 1 ,pStart + sizeLeftSub,inorder, iStart,iRoot -1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「左边界+1+左子树节点数目，右边界」的元素就对应了
        // 中序遍历中「根节点定位+1，右边界」的元素
        root.right = builder(preorder,pStart + 1 + sizeLeftSub,pEnd,inorder,iRoot + 1,iEnd);
        return root;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(n)

