## 题目

Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

请你实现 Trie 类：

* Trie() 初始化前缀树对象。
* void insert(String word) 向前缀树中插入字符串 word 。
* boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
* boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。



**示例**：

>输入
>["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
>[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
>输出
>[null, null, true, false, true, null, true]
>>解释
>Trie trie = new Trie();
>trie.insert("apple");
>trie.search("apple");   // 返回 True
>trie.search("app");     // 返回 False
>trie.startsWith("app"); // 返回 True
>trie.insert("app");
>trie.search("app");     // 返回 True



**提示**：

* 1 <= word.length, prefix.length <= 2000
* word 和 prefix 仅由小写英文字母组成
* insert、search 和 startsWith 调用次数 总计 不超过 3 * 10^4 次

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/implement-trie-prefix-tree](https://leetcode-cn.com/problems/implement-trie-prefix-tree?fileGuid=G8KJYPC9pdgJwjXv)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：字典树

Trie，又称前缀树或字典树，是一棵有根树，其每个节点包含以下字段：

* 指向子节点的指针数组 children。对于本题而言，数组长度为 26，即小写英文字母的数量。此时 children[0] 对应小写字母 a，children[1] 对应小写字母 b，…，children[25] 对应小写字母 z。
* 布尔字段 isEnd，表示该节点是否为字符串的结尾。

**插入字符串**

我们从字典树的根开始，插入字符串。对于当前字符对应的子节点，有两种情况：

* 子节点存在。沿着指针移动到子节点，继续处理下一个字符。
* 子节点不存在。创建一个新的子节点，记录在 children 数组的对应位置上，然后沿着指针移动到子节点，继续搜索下一个字符。

重复以上步骤，直到处理字符串的最后一个字符，然后将当前节点标记为字符串的结尾。

**查找前缀**

我们从字典树的根开始，查找前缀。对于当前字符对应的子节点，有两种情况：

* 子节点存在。沿着指针移动到子节点，继续搜索下一个字符。
* 子节点不存在。说明字典树中不包含该前缀，返回空指针。

重复以上步骤，直到返回空指针或搜索完前缀的最后一个字符。

若搜索到了前缀的末尾，就说明字典树中存在该前缀。此外，若前缀末尾对应节点的 isEnd 为真，则说明字典树中存在该字符串。

```java
class Trie {
    private boolean isEnd;
    private Trie[] children;
    /** Initialize your data structure here. */
    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie node = this; // 获取根节点
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            int index = ch - 'a';
            // 当前字符在前缀树中对应位置不存在，创建节点
            if(node.children[index] == null){
                node.children[index] = new Trie();
            }
            // 指向下一个节点
            node = node.children[index];
        }
        // 插入所有字符后，将最后一个字符标记为单词结束
        node.isEnd = true;
    }
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }
    // 搜索以前缀树中是否存在prefix，如果存在返回prefix串中最后一个字符所在的节点；否则返回null
    private Trie searchPrefix(String prefix){
        Trie node = this;
        for(int i = 0; i < prefix.length(); i++){
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if(node.children[index] == null)
                return null;
            node = node.children[index];
        }
        return node;
    }
}
```
时间复杂度：初始化为 O(1)，其余操作为 O(∣S∣)，其中∣S∣ 是每次插入或查询的字符串的长度。
空间复杂度：O(∣T∣⋅Σ)，其中 ∣T∣ 为所有插入字符串的长度之和，Σ 为字符集的大小，本题 Σ=26。




