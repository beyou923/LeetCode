class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head.next == null) return head;
        ListNode dummy = new ListNode();
        dummy.next = head; // 伪头结点
        // 第一段的尾和第三段的头
        ListNode firstTail = dummy, thirdStart = null;
        // 第二段的头和尾
        ListNode subStart = head, subEnd = null;
        ListNode curr = head;
        // 找第一段的尾和第二段的头
        for(int i = 1; i < left; i++) {
            firstTail = curr;
            curr = curr.next;
        }
        subStart = curr;
        // 找第二段的尾和第三段的头
        for(int i = left; i < right; i++) {
            curr = curr.next;
        }
        subEnd = curr;
        thirdStart = curr.next;
        // 将源链表分为三段
        firstTail.next = null;
        subEnd.next = null;
        // 反转第二段
        reverse(subStart);
        // 第一段的尾接第二段反转后的头
        firstTail.next = subEnd;
        // 第二段反转后的尾接第三段的头
        subStart.next = thirdStart;
        return dummy.next;

    }

    public ListNode reverse(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode pre = null, curr = head;
        while(curr != null) {
            ListNode tmp = curr;
            curr = curr.next;
            tmp.next = pre;
            pre = tmp;
        }
        return pre;
    }
}
