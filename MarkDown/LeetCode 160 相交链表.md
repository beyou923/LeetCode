## 题目描述

编写一个程序，找到两个单链表相交的起始节点。如下面的两个链表：

![图片](https://uploader.shimo.im/f/nhT73PgcTcFLR5iY.png!thumbnail?fileGuid=txwQVxjgjDrCTKK3)


在节点 c1 开始相交。

**示例 1：**

![图片](https://uploader.shimo.im/f/wqk0WsOOYsFuFy7i.png!thumbnail?fileGuid=txwQVxjgjDrCTKK3)

```shell
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
```
**示例 2：**
![图片](https://uploader.shimo.im/f/yz0FXuy6i0CxYDbV.png!thumbnail?fileGuid=txwQVxjgjDrCTKK3)

```shell
输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
```
**示例 3：**
![图片](https://uploader.shimo.im/f/h1ny0QJg3XvtfMKm.png!thumbnail?fileGuid=txwQVxjgjDrCTKK3)

```shell
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
解释：这两个链表不相交，因此返回 null。
```
**注意：**
* 如果两个链表没有交点，返回 null.
* 在返回结果后，两个链表仍须保持原有的结构。
* 可假定整个链表结构中没有循环。
* 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/intersection-of-two-linked-lists](https://leetcode-cn.com/problems/intersection-of-two-linked-lists?fileGuid=txwQVxjgjDrCTKK3)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 解法一

先统计两个链表长度,计算两个链表长度差sub,较长的链表先遍历sub个节点,让后两个链表一起遍历,判断两个链表节点是否相等.

### 解法二:双指针法

定义两个指针A和B,初始时候A和B分别指向两个链表头节点,当A遍历到链表A的末尾时候让A指向链表B的头节点;同样当B遍历到链表末尾时候让B指向链表A的头节点,当A和B相遇时就是它们的公共节点.为什么可以这样,可以这样思考,假设链表A的长度为a,链表B的长度为b,它们公共部分长度为c,设公共节点为node. A遍历完链表A,再从链表B的头节点开始遍历,当遍历到node时,A走过的路程为 a + (b - c);同样当B遍历到node时候经过的路程是 b + (a - c) ,可以看到A和B走过的路程是相同的,所以最后A和B相遇时就是node(如果A和B不为空的情况).

## 代码

### 解法一

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        int sub = Math.abs(lenA - lenB);
        int counter = 0;
        if(lenA > lenB) {
            while(counter < sub && headA != null){
            counter++;
            headA = headA.next;
            }  
        } else if(lenA < lenB){
            while(counter < sub && headB != null){
                counter++;
                headB = headB.next;
            }
        }
        // 同时遍历两个链表
        while(headA != null && headB != null){
            if(headA == headB){
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        } 
        return null; 
    }
    public int getLength(ListNode head){
        int len = 0;
        while(head != null){
            len++;
            head = head.next;
        }
        return len;
    }
}
```
### 解法二

```java
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
```
