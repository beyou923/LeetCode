## 题目

给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。

**示例 1**：

![图片](https://uploader.shimo.im/f/vECs3Crx6NohlYNG.png!thumbnail?fileGuid=VQjVXKGQxx8DXtkP)

>输入：head = [1,2,3,4,5], k = 2
>输出：[4,5,1,2,3]

**示例 2**：

![图片](https://uploader.shimo.im/f/Xz6BuwfbVOTXRPrF.png!thumbnail?fileGuid=VQjVXKGQxx8DXtkP)

>输入：head = [0,1,2], k = 4
>输出：[2,0,1]

**提示**：

* 链表中节点的数目在范围 [0, 500] 内
* -100 <= Node.val <= 100
* 0 <= k <= 2 * 10^9
## 思路

方法一：最容易想到的方法就是先求出链表的长度len，指针cur先走len - k % len步，cur的下一个节点就是新链表的头结点，将前后两段接起来就可以了

方法二：双指针法（快慢指针法）。先求出链表的长度len，快的指针先走k % len步，然后快慢指针同时走，当快指针走到链表末尾的时候，慢指针的下下一个节点就是新链表的头结点，用快指针接上原始链表的头结点即可

方法三：将链表头尾接起来，指针cur从链表头走len - k % len步，在cur处断开链接就好了

### 方法一

```java
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
    public ListNode rotateRight(ListNode head, int k) {
        int len = length(head);
        if(head == null || len == 0 || k == 0 || k % len == 0) return head;
        int step = len - k % len;
        if(step == 0 ) return head;
        ListNode cur = head, oldHead = head, newHead = null;
        for(int i = 1; i < step && cur != null; i++){
            cur = cur.next;
        }
        newHead = cur.next;
        cur.next = null;
        ListNode tmp = newHead;
        while(tmp.next != null){
            tmp = tmp.next;
        }
        tmp.next = oldHead;
        return newHead;
    }
    public int length(ListNode head) {
        int len = 0;
        while(head != null){
            len++;
            head = head.next;
        }
        return len;
    }
}
```
### 方法二

```java
class Solution2 {
    public ListNode rotateRight(ListNode head, int k) {
        int len = length(head);
        if(len == 0 || k == 0 || k % len == 0) return head;
        ListNode fast = head, slow = head;
        int step = 0;
        // 快指针先走 k % len 步
        while(fast != null && step <  k % len){
            fast = fast.next;
            step++;
        }
        // 快、慢指针开始同步走
        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        ListNode newHead = slow.next;
        fast.next = head;
        slow.next = null;
        return newHead;
    }
    public int length(ListNode head) {
        int len = 0;
        while(head != null){
            len++;
            head = head.next;
        }
        return len;
    }
}
```
### 方法三

```java
class Solution3 {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return null;
        int len = 1;
        ListNode cur = head;
        // 求链表长度
        while(cur.next != null){
            len++;
            cur = cur.next;
        }
        // 判断需不需要遍历
        if(len == 0 || k % len == 0) return head;
        // 首尾相连
        cur.next = head;
        // 遍历len - k % len个结点
        for(int step = 1; step < len - k % len; step++){
            head = head.next;
        }
        ListNode newHead = head.next;
        head.next = null;
        return newHead;
    }
}
```
**时间复杂度**：O(n)
**空间复杂度**：O(1)

