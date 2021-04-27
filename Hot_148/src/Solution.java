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

public class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        // 用分治法将链表拆分成一个个单独节点（单节点必有序）
        ListNode slow = head, fast = head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        // 归并有序链表
        ListNode h = new ListNode(0); // 添加一个虚拟头结点
        ListNode result = h;
        while(left != null && right != null){
            if(left.val < right.val){
                h.next = left;
                left = left.next;
            } else{
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        // 将余下的有序链表直接插入到h之后
        h.next = left != null ? left : right;
        return result.next;
    }
}
