package main

import "fmt"

/*
	给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
	不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

	链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
	给定数组 nums = [1,1,2],
	函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
	你不需要考虑数组中超出新长度后面的元素。
*/
func main() {
	nums := []int{-1,0,0,0,0,3,3}
	fmt.Println(removeDuplicates(nums))
}

// 解法和 第二十七题一样，就是用双指针法
func removeDuplicates(nums []int) int {
	n ,i := len(nums),0
	if n < 2 {
		return n
	}
	// val是重复元素 初始时候等于第一个元素
	val := nums[0]
	for j := 1; j < n;j++{
		// 选择要移除的元素
		if nums[i] == nums[j] {
			val = nums[i]
		}
		// 移动元素
		if nums[j] != val  {
			nums[i+1] = nums[j]
			i = i +1
		}
	}
	// 长度要加1
	return i +1
}