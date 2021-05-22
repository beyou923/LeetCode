## 题目

给定两个（单向）链表，判定它们是否相交并返回交点。请注意相交的定义基于节点的引用，而不是基于节点的值。换句话说，如果一个链表的第k个节点与另一个链表的第j个节点是同一节点（引用完全相同），则这两个链表相交。

**示例 1**：

>输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
>输出：Reference of the node with value = 8
>输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。

**示例 2**：

>输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
>输出：Reference of the node with value = 2
>输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。

**示例 3**：

>输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
>输出：null
>输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
>解释：这两个链表不相交，因此返回 null。

**注意**：

* 如果两个链表没有交点，返回 null 。
* 在返回结果后，两个链表仍须保持原有的结构。
* 可假定整个链表结构中没有循环。
* 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci](https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci?fileGuid=cyqkWwT9pvWPgqdg)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

如果两个链表相交，那么两个链表一定构成一个`Y`字形，在相交点之后的链一定使一样的，相交点之前的链长度可能不一样，只需要计算出两个链表的长度之差sub，较长的链表先走sub步，这是两个链表的长度一样，两个链表同时遍历，并比较二者的指针是否相等，如果相等就返回，否则继续遍历，如果遍历完链表仍然不相等，返回null

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA ==  null || headB == null) return null;
        // calculate link list A and B length
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        // 两个链表长度之差
        int sub = Math.abs(lenA - lenB);
        ListNode shorter = null;
        ListNode longer = null;
        // 确定谁长、谁短
        if(lenA >= lenB) {
            longer = headA;
            shorter = headB;
        } else {
            longer = headB;
            shorter = headA;
        }
        // 较长链表先走 sub 步，使两个链表剩余长度相等
        for(int i = 0; i < sub; i++) longer = longer.next;
        // 寻找两个链表的交点
        while(longer != shorter) {
            longer = longer.next;
            shorter = shorter.next;
        }
        if(longer == null || shorter == null) return null;
        return longer;
    }
    // get link list length
    public int getLength(ListNode head){
        if(head == null) return 0;
        int len = 0;
        while(head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
```
时间复杂度：O(m + n)
空间复杂度：O(1)

