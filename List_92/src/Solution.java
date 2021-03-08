/**
 *  反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。说明: 1 ≤ m ≤ n ≤ 链表长度。
 *
 *  输入: 1->2->3->4->5->NULL, m = 2, n = 4
 *  输出: 1->4->3->2->5->NULL
 *
 *  链接：https://leetcode-cn.com/problems/reverse-linked-list-ii/
 * **/

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}

public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode headNew = null;
        ListNode curr = head;
        int counter = 0;
        while (curr != null){
            counter++;
            ListNode nextNode = curr.next;
            // 尾插发
            if (counter < m || counter > n){
                headNew.next = curr;
                headNew = curr;
            } else {
                // 头插法
                curr.next = headNew;
                headNew = curr;
            }
            curr = nextNode;
        }
        return headNew;
    }
}
