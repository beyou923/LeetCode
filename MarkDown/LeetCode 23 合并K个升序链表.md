给你一个链表数组，每个链表都已经按升序排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。

**示例 1**：

>输入：lists = [[1,4,5],[1,3,4],[2,6]]
>输出：[1,1,2,3,4,4,5,6]
>解释：链表数组如下：
>[
>1->4->5,
>1->3->4,
>2->6
>]
>将它们合并到一个有序链表中得到。
>1->1->2->3->4->4->5->6

**示例 2**：

>输入：lists = []
>输出：[]

**示例 3**：

>输入：lists = [[]]
>输出：[]

**提示**：

* k == lists.length
* 0 <= k <= 10^4
* 0 <= lists[i].length <= 500
* -10^4 <= lists[i][j] <= 10^4
* lists[i] 按 升序 排列
* lists[i].length 的总和不超过 10^4

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/merge-k-sorted-lists](https://leetcode-cn.com/problems/merge-k-sorted-lists?fileGuid=8w8rptHd3cgRqkqj)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 方法一：顺序合并

这是最容易想到的方法，但是最容易想到的方法效率往往不是最高的。

```java
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class Solution1 {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length < 1) return null;
        if(lists.length < 2) return lists[0];
        // 顺序合并
        for(int i = 0; i < lists.length - 1; i++){
            ListNode merge = mergeLink(lists[i], lists[ i + 1]);
            lists[i + 1] = merge;
        }
        return lists[lists.length - 1];
    }
    // 归并两个有序链表
    public ListNode mergeLink(ListNode head1, ListNode head2){
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
**时间复杂度**：O(k^2n)
**空间复杂度**：O(1)

### 方法二：分治法

```java
public class Solution2 {
    // 分治法
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length < 1) return null;
        return merge(lists,0,lists.length - 1);
    }
    public ListNode merge(ListNode[] lists, int star, int end){
        if (star == end) return lists[star];
        int mid = star + (end - star) / 2;
        ListNode left = merge(lists,star, mid);
        ListNode right = merge(lists,mid + 1, end);
        return mergeLink(left,right);
    }
    // 归并两个有序链表
    public ListNode mergeLink(ListNode head1, ListNode head2){
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
时间复杂度：O(kn * logk)
空间复杂度：O(logk)

