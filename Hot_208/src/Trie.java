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
