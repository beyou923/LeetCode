import java.util.HashMap;
import java.util.Map;

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

class Solution1 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length < 1) return null;

        return builder(preorder,0,preorder.length - 1,inorder,0,inorder.length - 1);
    }

    public TreeNode builder(int[] preorder, int pStart, int pEnd,int[] inorder,int iStart, int iEnd){
        if(pStart > pEnd || iStart > iEnd) return null;

        int index = 0;
        for(index = iStart; index <= iEnd; index++){
            if(preorder[pStart] == inorder[index])
                break;
        }

        TreeNode root = new TreeNode(preorder[pStart]);
        root.left = builder(preorder,pStart + 1, pStart + index - iStart,inorder,iStart, index -1);
        root.right = builder(preorder,pStart + index - iStart + 1, pEnd, inorder, index + 1, iEnd);

        return root;
    }
}

class Solution {
    Map<Integer,Integer> index;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length < 1) return null;
        int len = preorder.length;
        index = new HashMap<>();
        for(int i = 0; i < len; i++){
            index.put(inorder[i],i);
        }

        return builder(preorder,0,len -1,inorder, 0, len -1);
    }

    public TreeNode builder(int[] preorder, int pStart,int pEnd, int[] inorder, int iStart,int iEnd){
        if(pStart > pEnd)
            return null;
        // 前序遍历中的第一个节点就是根节点
        int preRoot = pStart;
        // 在中序遍历中定位根节点
        int iRoot = index.get(preorder[preRoot]);
        // 创建根节点
        TreeNode root = new TreeNode(preorder[preRoot]);
        // 得到左子树中的节点数目
        int sizeLeftSub = iRoot - iStart;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「左边界+1, 左边界+左子树节点数目」个元素就对应了
        // 中序遍历中「左边界，根节点定位-1」的元素
        root.left = builder(preorder, pStart + 1 ,pStart + sizeLeftSub,inorder, iStart,iRoot -1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「左边界+1+左子树节点数目，右边界」的元素就对应了
        // 中序遍历中「根节点定位+1，右边界」的元素
        root.right = builder(preorder,pStart + 1 + sizeLeftSub,pEnd,inorder,iRoot + 1,iEnd);
        return root;
    }
}
