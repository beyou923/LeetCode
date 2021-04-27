/**
 * Definition for singly-linked list.
 *  */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution1 {
    public ListNode rotateRight(ListNode head, int k) {
        int len = length(head);
        if(head == null || len == 0 || k == 0 || k % len == 0) return head;

        int step = len - k % len;
        if(step == 0 ) return head;
        ListNode cur = head, oldHead = head, newHead = null;
        for(int i = 1; i < step && cur != null; i++){
            cur = cur.next;
        }
        newHead = cur.next;
        cur.next = null;
        ListNode tmp = newHead;
        while(tmp.next != null){
            tmp = tmp.next;
        }
        tmp.next = oldHead;
        return newHead;
    }

    public int length(ListNode head) {
        int len = 0;
        while(head != null){
            len++;
            head = head.next;
        }
        return len;
    }
}
