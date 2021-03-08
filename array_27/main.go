package main

import (
	"fmt"
)
/*
	给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
	不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
	元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
	链接：https://leetcode-cn.com/problems/remove-element

	给定 nums = [3,2,2,3], val = 3,
	函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
	你不需要考虑数组中超出新长度后面的元素。

*/
func main() {
	nums := []int{0,1,2,2,3,0,4,2}
	fmt.Println(removeElement(nums,2))
}

//考虑顺序
func removeElement(nums []int, val int) int {
	i := 0 // i,j分别是慢、快指针
	for j := 0; j < len(nums); j++ {
		if nums[j] != val { //不相等的就是需要左移的
			nums[i] = nums[j]
			i++
		}
	}
	return i
}

//