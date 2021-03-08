/**
 *  反转一个单链表。
 *  示例:
 *      输入: 1->2->3->4->5->NULL
 *      输出: 5->4->3->2->1->NULL
 * */

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }

public class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode headNew = null; // 逆序链表头
        ListNode curr = head; // 当前节点
        while (curr != null) {
            ListNode nextNode = curr.next;// 记住下一个节点防止断链
            //注意以下两句代码不能调换位置
            curr.next = headNew; // 头插法
            headNew = curr;
            curr = nextNode;// 遍历链表
        }
        return headNew;
    }
}
