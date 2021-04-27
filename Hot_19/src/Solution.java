class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = getLength(head);
        ListNode dummy = new ListNode(0,head); // 链表头添加哑结点，减少表头为空的判断
        ListNode cur = dummy;
        for(int i = 1; i < len - n + 1; i++){ // 注意循环条件
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode result = dummy.next;
        return result;
    }
    public int getLength(ListNode head){
        int len = 0;
        while(head != null){
            len++;
            head = head.next;
        }

        return len;
    }
}