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
        ListNode A = headA, B = headB;
        while(A != B){
            A = (A != null ? A.next : headB);
            B = (B != null ? B.next : headA);
        }
        return A;
    }

    public static void main(String[] args){
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);

        ListNode headB = new ListNode(4);
        headB.next = new ListNode(5);
        headB.next.next = new ListNode(6);
        Solution s = new Solution();
        ListNode res = s.getIntersectionNode(headA,headB);
        if (res != null)
            System.out.println("ok");
        else
            System.out.println("no");
    }
}