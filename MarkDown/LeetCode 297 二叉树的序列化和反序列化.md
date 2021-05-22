## 题目

序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

**示例 1**：

![图片](https://uploader.shimo.im/f/zsARDF5h6oFDdBFB.png!thumbnail?fileGuid=d63C8jrXvTRXcyxX)

>输入：root = [1,2,3,null,null,4,5]
>输出：[1,2,3,null,null,4,5]

**示例 2**：

>输入：root = []
>输出：[]

**示例 3**：

>输入：root = [1]
>输出：[1]

**示例 4**：

>输入：root = [1,2]
>输出：[1,2]

**提示**：

* 树中结点数在范围 [0, 10^4] 内
* -1000 <= Node.val <= 1000

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree](https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree?fileGuid=d63C8jrXvTRXcyxX)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：BFS

用层序遍历二叉树进行序列化，同样通过模拟层序遍历二叉树的方式反序列化二叉树

```java
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
```
时间复杂度：O(n)
空间复杂度：O(n)

