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
