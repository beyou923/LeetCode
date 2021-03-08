package main

import (
	"fmt"
)
/* 给定一个整数数组 nums和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。

	你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

	链接：https://leetcode-cn.com/problems/two-sum
*/
func main() {
	nums := []int{2,7,11,15}
	target := 17
	ret := twoSum(nums,target)
	fmt.Println(ret)
}

func twoSum(nums []int, target int) []int {
	hashTable := map[int]int{}

	for index,x := range nums {
		if value,ok := hashTable[target - x] ; ok {
			return []int{index,value}
		}
		hashTable[x] = index
	}
	return nil
}
