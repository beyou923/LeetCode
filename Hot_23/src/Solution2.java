public class Solution2 {
    // 分治法
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length < 1) return null;
        return merge(lists,0,lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int star, int end){
        if (star == end) return lists[star];

        int mid = star + (end - star) / 2;
        ListNode left = merge(lists,star, mid);
        ListNode right = merge(lists,mid + 1, end);

        return mergeLink(left,right);
    }

    // 归并两个有序链表
    public ListNode mergeLink(ListNode head1, ListNode head2){
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
