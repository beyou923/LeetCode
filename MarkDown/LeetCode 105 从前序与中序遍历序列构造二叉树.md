## 题目

根据一棵树的前序遍历与中序遍历构造二叉树。注意:你可以假设树中没有重复的元素。

例如，给出

>前序遍历 preorder = [3,9,20,15,7]
>中序遍历 inorder = [9,3,15,20,7]

返回如下的二叉树：

>3
>/ \
>9  20
>/  \
>15   7

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal?fileGuid=V6RR3YrXgHDKXqR3)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路:递归

先构建根节点，并找到根节点在中序遍历中的下标index，index之前是左子树，index之后的节点是右子树，再递归地构建左右子树

```java
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length < 1) return null;
        return builder(preorder,0,preorder.length - 1,inorder,0,inorder.length - 1);
    }
    public TreeNode builder(int[] preorder, int pStart, int pEnd,int[] inorder,int iStart, int iEnd){
        if(pStart > pEnd || iStart > iEnd) return null;
        int index = 0;
        for(index = iStart; index <= iEnd; index++){
            if(preorder[pStart] == inorder[index])
                break;
        }
        TreeNode root = new TreeNode(preorder[pStart]);
        root.left = builder(preorder,pStart + 1, pStart + index - iStart,inorder,iStart, index -1);
        root.right = builder(preorder,pStart + index - iStart + 1, pEnd, inorder, index + 1, iEnd);
        return root;
    }
}
```
上述方法中每次找根节点在中序遍历中的下标都要一个个遍历，可以用用HaspMap优化这个操作，将中序序列中的节点作为map的key，其下标作为值，这样就可以在O(1)的时间找到根节点的下标了。
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

