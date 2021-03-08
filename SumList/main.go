package main

import "fmt"

/*	给出两个非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。

	如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

	您可以假设除了数字 0 之外，这两个数都不会以 0开头。

	链接：https://leetcode-cn.com/problems/add-two-numbers
*/

//Definition for singly-linked list.
type ListNode struct {
     Val int
     Next *ListNode
 }

func addTwoNumbers(l1 *ListNode, l2 *ListNode) (head *ListNode) {
	var tail *ListNode
	carry := 0

	for l1 != nil || l2 != nil {
		n1 , n2 := 0,0

		if l1 != nil {
			n1 = l1.Val
			l1 = l1.Next
		}

		if l2 != nil {
			n2 = l2.Val
			l2 = l2.Next
		}

		sum := n1 + n2 + carry
		sum, carry = sum % 10, sum / 10

		if head == nil {
			head = &ListNode{Val: sum}
			tail = head
	} else {
		tail.Next = &ListNode{Val: sum}
		tail = tail.Next
		}
	}
	if carry > 0 {
		tail.Next = &ListNode{Val: carry}
		tail = tail.Next
	}
	return head
}

func main() {
	var head1 = new(ListNode)
	var node1 = new(ListNode)
	var node2 = new(ListNode)
	head1.Val = 2
	node1.Val, node2.Val = 4, 7
	head1.Next, node1.Next = node1, node2

	var head2 = new(ListNode)
	var node3 = new(ListNode)
	var node4 = new(ListNode)
	head2.Val = 5
	node3.Val, node4.Val = 6, 4
	head2.Next, node3.Next = node3, node4

	head := addTwoNumbers(head1,head2)
	for head != nil {
		fmt.Println(head.Val)
		head = head.Next
	}
}

