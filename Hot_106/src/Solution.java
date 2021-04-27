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

class Solution {
    public int[] inorder;
    public int[] postorder;
    public int lastIndex;
    Map<Integer,Integer> indexMap;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        // 从后序遍历的最后一个元素开始
        lastIndex = inorder.length - 1;
        indexMap = new HashMap<>();
        for(int i = 0; i <= lastIndex; i++)
            indexMap.put(inorder[i],i);

        return builder(0, lastIndex);

    }

    // left/right分别代表从中序遍历序列中[left,right]构建子树
    public TreeNode builder(int left, int right){
        if(left > right) return null;
        // 后序遍历中的最后一个节点就是根节点
        int rootVal = postorder[lastIndex];
        // 创建根节点
        TreeNode root = new TreeNode(rootVal);
        // 在中序遍历中定位根节点
        int index = indexMap.get(postorder[lastIndex]);
        // 下标减1
        lastIndex--;

        // 递归构建右子树
        root.right = builder(index + 1, right);
        // 递归构建左子树
        root.left = builder(left, index - 1);

        return root;
    }
}
