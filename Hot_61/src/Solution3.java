class Solution3 {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return null;
        int len = 1;
        ListNode cur = head;
        // 求链表长度
        while(cur.next != null){
            len++;
            cur = cur.next;
        }
        // 判断需不需要遍历
        if(len == 0 || k % len == 0) return head;
        // 首尾相连
        cur.next = head;
        // 遍历len - k % len个结点
        for(int step = 1; step < len - k % len; step++){
            head = head.next;
        }
        ListNode newHead = head.next;
        head.next = null;
        return newHead;
    }
}
