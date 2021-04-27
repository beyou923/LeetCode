class Solution2 {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode node = head;
        int length = 0;
        // 计算链表长度
        while(node != null){
            length++;
            node = node.next;
        }
        // 分治法对子链表排序
        ListNode dummy = new ListNode(0,head); // 添加一个伪头结点
        // TODO：左移相当于乘2；右移相当于除以2
        // TODO: 子链表长度按照2^n递增 n初始值为0，对应sunLength = 1
        for(int sunLength = 1; sunLength < length; sunLength <<= 1){
            ListNode prev = dummy, curr = dummy.next;
            while(curr != null){
                ListNode head1 = curr;
                // TODO: 注意前后两个for循环的终止条件对curr的判断不一样
                for(int i = 1; i < sunLength && curr.next != null; i++){
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for(int i = 1; i < sunLength && curr != null; i++){
                    curr = curr.next;
                }

                ListNode next = null;
                if(curr != null){
                    next = curr.next;
                    curr.next = null;
                }
                // merge
                ListNode merge =  mergeLink(head1,head2);
                prev.next = merge;
                while(prev.next != null){
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummy.next;
    }
    // 归并两个有序链表
    ListNode mergeLink(ListNode head1, ListNode head2){
        if(head1 == null || head2 == null) return head1 == null ? head2 : head1;
        ListNode dummy = new ListNode(0);
        ListNode tmp = dummy;
        while(head1 != null && head2 != null){
            if(head1.val <= head2.val){
                tmp.next = head1;
                head1 = head1.next;
            } else{
                tmp.next = head2;
                head2 = head2.next;
            }
            tmp = tmp.next;
        }
        // 将剩余的有序结点追加到已排序链表的末尾
        if(head1 != null)
            tmp.next = head1;
        if(head2 != null)
            tmp.next = head2;

        return dummy.next;
    }
}
