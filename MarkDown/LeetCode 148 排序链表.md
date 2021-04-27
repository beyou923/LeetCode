## 题目

给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

**进阶**：

* 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？

**示例 1**：

![图片](https://uploader.shimo.im/f/TpsWFo4aqtURcTQN.png!thumbnail?fileGuid=ktD9Ct6qx6r9ywdP)

>输入：head = [4,2,1,3]
>输出：[1,2,3,4]

**示例 2**：

![图片](https://uploader.shimo.im/f/RpTV6FnCAIxPByd2.png!thumbnail?fileGuid=ktD9Ct6qx6r9ywdP)

>输入：head = [-1,5,3,4,0]
>输出：[-1,0,3,4,5]

**示例 3**：

>输入：head = []
>输出：[]

**提示**：

* 链表中节点的数目在范围 [0, 5 * 10^4] 内
* -10^5 <= Node.val <= 10^5

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/sort-list](https://leetcode-cn.com/problems/sort-list?fileGuid=ktD9Ct6qx6r9ywdP)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路:归并排序

这道题考虑时间复杂度更低的排序算法。题目的进阶问题要求达到O(nlogn) 的时间复杂度和 O(1) 的空间复杂度，时间复杂度是 O(nlogn) 的排序算法包括归并排序、堆排序和快速排序，快速排序的最差时间复杂度是 O(n^2)，其中最适合链表的排序算法是归并排序。

归并排序基于分治算法。最容易想到的实现方式是自顶向下的递归实现，考虑到递归调用的栈空间，自顶向下归并排序的空间复杂度是O(logn)。如果要达到O(1) 的空间复杂度，则需要使用自底向上的实现方式。

### 方法一：自顶向下归并排序（递归实现）

对链表自顶向下归并排序的过程如下。

1. 找到链表的中点，以中点为分界，将链表拆分成两个子链表。寻找链表的中点可以使用快慢指针的做法，快指针每次移动 22 步，慢指针每次移动 11 步，当快指针到达链表末尾时，慢指针指向的链表节点即为链表的中点。
2. 对两个子链表分别排序。
3. 将两个排序后的子链表合并，得到完整的排序后的链表。可以使用「[21. 合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/?fileGuid=ktD9Ct6qx6r9ywdP)」的做法，将两个有序的子链表进行合并。

![图片](https://uploader.shimo.im/f/28OF0DCIJJILDyJh.png!thumbnail?fileGuid=ktD9Ct6qx6r9ywdP)

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
```
**时间复杂度**：O(nlogn)，其中n 是链表的长度。
**空间复杂度**：O(logn)，其中 n 是链表的长度。空间复杂度主要取决于递归调用的栈空间。

### 方法二：自底向上归并排序

使用自底向上的方法实现归并排序，则可以达到 O(1) 的空间复杂度，本质在用循环模拟递归。首先求得链表的长度 \textit{length}length，然后将链表拆分成子链表进行合并。

具体做法如下：

1. 用`subLength`表示每次需要排序的子链表的长度，初始时`subLength=1`
2. 每次将链表拆分成若干个长度为`subLength`的子链表（最后一个子链表的长度可以小于`subLength`，按照每两个子链表一组进行合并，合并后即可得到若干个长度为`sunLength x 2`的有序子链表（最后一个子链表的长度可以小于`sunLength x 2`）。合并两个子链表仍然使用「[21. 合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/?fileGuid=ktD9Ct6qx6r9ywdP)」的做法。
3. 将`subLength`的值加倍，重复第 2 步，对更长的有序子链表进行合并操作， 直到有序子链表的长度大于或等于`length`，整个链表排序完毕。
```java
class Solution {
public ListNode sortList(ListNode head) {
if(head == null || head.next == null) return head;
ListNode node = head;
int length = 0;
// 计算链表长度
while(node != null){
length++;
node = node.next;
}
// 分治法对子链表排序
ListNode dummy = new ListNode(0,head); // 添加一个伪头结点
// TODO：左移相当于乘2；右移相当于除以2
// TODO: 子链表长度按照2^n递增 n初始值为0，对应sunLength = 1
for(int sunLength = 1; sunLength < length; sunLength <<= 1){
ListNode prev = dummy, curr = dummy.next;
while(curr != null){
ListNode head1 = curr;
// TODO: 注意前后两个for循环的终止条件对curr的判断不一样
for(int i = 1; i < sunLength && curr.next != null; i++){
curr = curr.next;
}
ListNode head2 = curr.next;
curr.next = null;
curr = head2;
for(int i = 1; i < sunLength && curr != null; i++){
curr = curr.next;
}
ListNode next = null;
if(curr != null){
next = curr.next;
curr.next = null;
}
// merge
ListNode merge =  mergeLink(head1,head2);
prev.next = merge;
while(prev.next != null){
prev = prev.next;
}
curr = next;
}
}
return dummy.next;
}
// 归并两个有序链表
ListNode mergeLink(ListNode head1, ListNode head2){
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
```
**时间复杂度**：O(nlogn)，其中 n 是链表的长度。
**空间复杂度**：O(1)。

