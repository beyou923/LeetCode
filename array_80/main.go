package main

import "fmt"

/*
	给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
	不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

	给定 nums = [1,1,1,2,2,3],
	函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
	你不需要考虑数组中超出新长度后面的元素。

	链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
*/

func removeDuplicates(nums []int) int {
	n := len(nums)
	// 长度小于3直接返回
	if n < 3 {
		return n
	}
	i,count := 1,1
	for j := 1; j < n; j++ {
		if nums[j] == nums[j-1] {
			count++
		} else {
			count = 1
		}
		if count <= 2 {
			nums[i] = nums[j]
			i = i +1
		}
	}
	return i
}

func removeDuplicates1(nums []int) int {
	count := 1
	i := 1
	for j := 1; j< len(nums); j++ {
		if nums[j] == nums[j-1]  {
			count++
		} else if nums[j] == nums[j-1] && count > 2 {
			i++
			nums[i] = nums[j]
			count =1
		}
	}
	return i
}
func main() {
	nums := []int{1,1,1,2,2,3}
	fmt.Println(removeDuplicates1(nums))
}