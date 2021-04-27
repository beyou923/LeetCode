class Solution2 {
    public ListNode rotateRight(ListNode head, int k) {
        int len = length(head);
        if(len == 0 || k == 0 || k % len == 0) return head;
        ListNode fast = head, slow = head;
        int step = 0;
        // 快指针先走 k % len 步
        while(fast != null && step <  k % len){
            fast = fast.next;
            step++;
        }
        // 快、慢指针开始同步走
        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }

        ListNode newHead = slow.next;
        fast.next = head;
        slow.next = null;
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
