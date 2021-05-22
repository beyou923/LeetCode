public class Solution {
}

class LinkNode{
    public int val;
    public LinkNode next;
    public LinkNode(){}
    public LinkNode(int val){
        this.val = val;
    }
}
class MyLinkedList {
    LinkNode head; // 哑结点
    int size; // 节点个数
    public MyLinkedList() {
        head = new LinkNode();
        size = 0;
    }

    public int get(int index) {
        if(index >= size || index < 0) return -1;
        LinkNode curr = head;
        // 从哑结点开始遍历，走index + 1 步
        for(int i = 0; i < index + 1; i++)
            curr =curr.next;
        return curr.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0,val);
    }

    public void addAtTail(int val) {
        addAtIndex(size,val);
    }

    public void addAtIndex(int index, int val) {
        if(index > size) return;
        if(index < 0) index = 0;
        LinkNode node = new LinkNode(val);
        LinkNode curr = head;
        for(int i = 0; i < index; i++)
            curr = curr.next;
        node.next = curr.next;
        curr.next = node;
        size++;
    }

    public void deleteAtIndex(int index) {
        if(size <= 0 || index >= size || index < 0) return;
        LinkNode curr = head;
        for(int i = 0; i < index; i++)
            curr = curr.next;
        curr.next = curr.next.next;
        size--;
    }
}
