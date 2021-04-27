## 题目描述

请判断一个链表是否为回文链表。

**示例 1:**

```shell
输入: 1->2
输出: false
```
**示例 2:**
```shell
输入: 1->2->2->1
输出: true
```
**进阶：**
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/palindrome-linked-list](https://leetcode-cn.com/problems/palindrome-linked-list?fileGuid=xhPgrG6cGjpk6rVg)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

将链表中的各节点值压入栈中,再同时遍历链表和栈,如果链表节点值和栈顶元素相等则继续遍历,否则直接返回false

## 代码

```java
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
```
## 复杂度分析

### 时间复杂度: O(n)

### 空间复杂度:O(n)

