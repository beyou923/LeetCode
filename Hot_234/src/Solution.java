import java.util.LinkedList;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null) return true;
        LinkedList<Integer> stack = new LinkedList<Integer>();
        ListNode cur = head;
        while(cur != null){
            stack.addLast(cur.val);
            cur = cur.next;
        }
        while(head != null && !stack.isEmpty()){
            int value = stack.getLast();
            if(value != head.val){
                return false;
            }
            head = head.next;
            stack.removeLast();
        }
        return true;
    }
}