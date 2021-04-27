## 题目描述

给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

进阶：你能尝试使用一趟扫描实现吗？

**示例 1：**

![图片](https://uploader.shimo.im/f/4f4EW8ipHknXn5Nc.png!thumbnail?fileGuid=9cQPh66kYVpRxPQj)

```shell
输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]
```
**示例 2：**
```shell
输入：head = [1], n = 1
输出：[]
```
**示例 3：**
```shell
输入：head = [1,2], n = 1
输出：[1]
```
**提示：**
* 链表中结点的数目为 sz
* 1 <= sz <= 30
* 0 <= Node.val <= 100
* 1 <= n <= sz

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

在对链表进行操作时，一种常用的技巧是添加一个哑节点（dummy node），它的 next 指针指向链表的头节点。这样一来，就不需要对头节点进行特殊的判断了。例如，在本题中，如果我们要删除节点 y，我们需要知道节点 y 的前驱节点 x，并将 x 的指针指向 y 的后继节点。但由于头节点不存在前驱节点，因此我们需要在删除头节点时进行特殊判断。但如果我们添加了哑节点，那么头节点的前驱节点就是哑节点本身，此时就只需要考虑通用的情况即可。

一种容易想到的方法是，我们首先从头节点开始对链表进行一次遍历，得到链表的长度 L。随后我们再从头节点开始对链表进行一次遍历，当遍历到第 L-n+1 个节点时，它就是我们需要删除的节点。

## 代码

```java
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = getLength(head);
        ListNode dummy = new ListNode(0,head); // 链表头添加哑结点，减少表头为空的判断
        ListNode cur = dummy;
        for(int i = 1; i < len - n + 1; i++){ // 注意循环条件
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode result = dummy.next;
        return result;
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
## 复杂度分析

### 时间复杂度：O(n)

### 空间复杂度：O(1)


