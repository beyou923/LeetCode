## 题目

运用你所掌握的数据结构，设计和实现一个[LRU (最近最少使用) 缓存机制](https://baike.baidu.com/item/LRU?fileGuid=pxPrRxX6YYVRTPwg)。

实现 LRUCache 类：

* LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
* int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
* void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

**进阶**：你是否可以在 O(1) 时间复杂度内完成这两种操作？

**示例**：

>输入
>["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
>[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
>输出
>[null, null, null, 1, null, -1, null, -1, 3, 4]
>>解释
>LRUCache lRUCache = new LRUCache(2);
>lRUCache.put(1, 1); // 缓存是 {1=1}
>lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
>lRUCache.get(1);    // 返回 1
>lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
>lRUCache.get(2);    // 返回 -1 (未找到)
>lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
>lRUCache.get(1);    // 返回 -1 (未找到)
>lRUCache.get(3);    // 返回 3
>lRUCache.get(4);    // 返回 4

**提示**：

* 1 <= capacity <= 3000
* 0 <= key <= 3000
* 0 <= value <= 10^4
* 最多调用 3 * 10^4 次 get 和 put

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/lru-cache](https://leetcode-cn.com/problems/lru-cache?fileGuid=pxPrRxX6YYVRTPwg)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：双向链表 + 哈希表

LRU 缓存机制可以通过哈希表辅以双向链表实现，我们用一个哈希表和一个双向链表维护所有在缓存中的键值对。

* 双向链表按照被使用的顺序存储了这些键值对，靠近头部的键值对是最近使用的，而靠近尾部的键值对是最久未使用的。
* 哈希表即为普通的哈希映射（HashMap），通过缓存数据的键映射到其在双向链表中的位置。

这样以来，我们首先使用哈希表进行定位，找出缓存项在双向链表中的位置，随后将其移动到双向链表的头部，即可在O(1) 的时间内完成 get 或者 put 操作。具体的方法如下：

* 对于 get 操作，首先判断 key 是否存在：
    * 如果 key 不存在，则返回 -1−1；
    * 如果 key 存在，则 key 对应的节点是最近被使用的节点。通过哈希表定位到该节点在双向链表中的位置，并将其移动到双向链表的头部，最后返回该节点的值。
* 对于 put 操作，首先判断 key 是否存在：
    * 如果 key 不存在，使用 key 和 value 创建一个新的节点，在双向链表的头部添加该节点，并将 key 和该节点添加进哈希表中。然后判断双向链表的节点数是否超出容量，如果超出容量，则删除双向链表的尾部节点，并删除哈希表中对应的项；
    * 如果 key 存在，则与 get 操作类似，先通过哈希表定位，再将对应的节点的值更新为 value，并将该节点移到双向链表的头部。

上述各项操作中，访问哈希表的时间复杂度为O(1)，在双向链表的头部添加节点、在双向链表的尾部删除节点的复杂度也为 O(1)。而将一个节点移到双向链表的头部，可以分成「删除该节点」和「在双向链表的头部添加节点」两步操作，都可以在O(1) 时间内完成。

**注意**：在双向链表的实现中，使用一个伪头部（dummy head）和伪尾部（dummy tail）标记界限，这样在添加节点和删除节点的时候就不需要检查相邻的节点是否存在。

```java
import java.util.HashMap;
import java.util.Map;
public class LRUCache {
    // 双向链表
    class DLinkedList {
        int key;
        int value;
        DLinkedList pre;
        DLinkedList next;
        public DLinkedList(){}
        public DLinkedList(int key,int value){
            this.key = key;
            this.value = value;
        }
    }
    private int size;
    private int capacity;
    private Map<Integer,DLinkedList> cache = new HashMap<>();
    private DLinkedList head;
    private DLinkedList tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        // 伪头部/伪尾部节点
        head = new DLinkedList();
        tail = new DLinkedList();
        head.next = tail;
        tail.pre = head;
    }
    public int get(int key) {
        DLinkedList node = cache.get(key);
        if(node == null)
            return -1;
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }
    public void put(int key, int value) {
        DLinkedList node = cache.get(key);
        // 缓存中没有，插入节点
        if(node == null){
            // 如果key不存在，创建一个新节点
            DLinkedList newNode = new DLinkedList(key,value);
            // 添加进哈希表
            cache.put(key,newNode);
            // 将新节点移动到双向链表的头部
            addToHead(newNode);
            size++;
            // 判断是否溢出
            if(size > capacity){
                // 删除双向链表末尾节点
                DLinkedList tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                size--;
            }
        } else{
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }
    public void moveToHead(DLinkedList node){
        removeNode(node);
        addToHead(node);
    }
    public void removeNode(DLinkedList node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    public void addToHead(DLinkedList node){
        node.pre = head;
        node.next = head.next;
        head.next = node;
        node.next.pre = node;
    }
    public DLinkedList removeTail(){
        DLinkedList res = tail.pre;
        removeNode(res);
        return res;
    }
}
```
**时间复杂度**：O(1)
**空间复杂度**：O(capacity)

