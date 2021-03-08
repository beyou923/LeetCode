package main

import "fmt"

/*
	给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
	说明:
		尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。要求使用空间复杂度为 O(1) 的 原地 算法。
	示例 1:
	输入: [1,2,3,4,5,6,7] 和 k = 3
	输出: [5,6,7,1,2,3,4]
	解释:
		向右旋转 1 步: [7,1,2,3,4,5,6]
		向右旋转 2 步: [6,7,1,2,3,4,5]
		向右旋转 3 步: [5,6,7,1,2,3,4]
	链接：https://leetcode-cn.com/problems/rotate-array
	批注：这个问题等价于循环右移

*/
func rotate(nums []int, k int)  {
	n := len(nums)
	if n < 1  {
		return
	}
	// 当右移位数大于数组长度时候，需要对右移位数变换
	if n < k {
		k = k % n
	}
	demarcation  := n - k
	// 1. 将分界点右边的元素反序
	reverse(nums[:demarcation])
	// 2. 将分节点左边的元素反序
	reverse(nums[demarcation:])
	// 3. 所有元素反序
	reverse(nums)

}

// 将数组元素反序
func reverse(nums[]int) {
	n := len(nums)
	if n < 2 {
		return
	}
	for i,j := 0,n-1; i < j; i,j = i+1,j-1 {
		tmp := nums[j]
		nums[j] = nums[i]
		nums[i] = tmp
	}
}

func main() {
	nums := []int{1,2}
	rotate(nums,3)
	fmt.Println(nums)
}