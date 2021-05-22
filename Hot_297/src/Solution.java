import java.util.*;

public class Solution {
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Codec {

    // Encodes a tree to a single string.
    public List<String> serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<String> list = new ArrayList<>();
        if(root == null) return list;
        TreeNode currNode;
        queue.add(root);
        while(!queue.isEmpty()){
            currNode = queue.poll();

            if (currNode != null) {
                // 将出队列的节点加入list
                list.add(String.valueOf(currNode.val));
                queue.add(currNode.left);
                queue.add(currNode.right);

            } else {
                list.add("null");
            }
        }
        return list;
    }

    // Decodes your encoded data to tree.
    // TODO:如果用层序遍历序列化二叉树，在反序列化的时候通过模拟层序遍历的过程反序列化
    public TreeNode deserialize(List<String> data) {
        if (data == null || data.size() == 0) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(data.get(0)));
        int index = 1;
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode currNode = queue.poll();
            if (!data.get(index).equals("null")){
                currNode.left = new TreeNode(Integer.parseInt(data.get(index)));
                queue.add(currNode.left);
            }
            index++;
            if (!data.get(index).equals("null")){
                currNode.right = new TreeNode(Integer.parseInt(data.get(index)));
                queue.add(currNode.right);
            }
            index++;
        }
        return root;
    }
    // TODO : 如果是通过层序序列化二叉树就不能用这种方式反序列二叉树 注意！！！！
//    public TreeNode deserialize(List<String> data) {
//        if (data == null || data.size() == 0) return null;
//        if(data.get(0).equals("null")){
//            data.remove(0);
//            return null;
//        }
//        TreeNode root = new TreeNode(Integer.parseInt(data.get(0)));
//        data.remove(0);
//        root.left = deserialize(data);
//        root.right = deserialize(data);
//        return root;
//    }

}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
