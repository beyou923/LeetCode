class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA ==  null || headB == null) return null;
        // calculate link list A and B length
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        // 两个链表长度之差
        int sub = Math.abs(lenA - lenB);
        ListNode shorter = null;
        ListNode longer = null;
        // 确定谁长、谁短
        if(lenA >= lenB) {
            longer = headA;
            shorter = headB;
        } else {
            longer = headB;
            shorter = headA;
        }
        // 较长链表先走 sub 步，使两个链表剩余长度相等
        for(int i = 0; i < sub; i++) longer = longer.next;
        // 寻找两个链表的交点
        while(longer != shorter) {
            longer = longer.next;
            shorter = shorter.next;
        }
        if(longer == null || shorter == null) return null;
        return longer;
    }
    // get link list length
    public int getLength(ListNode head){
        if(head == null) return 0;
        int len = 0;
        while(head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
