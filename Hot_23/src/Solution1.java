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
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length < 1) return null;
        if(lists.length < 2) return lists[0];
        // 顺序合并
        for(int i = 0; i < lists.length - 1; i++){
            ListNode merge = mergeLink(lists[i], lists[ i + 1]);
            lists[i + 1] = merge;
        }
        return lists[lists.length - 1];
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