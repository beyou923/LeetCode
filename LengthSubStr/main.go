package main

import "fmt"

/*	给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
	输入: "abcabcbb"
	输出: 3
	解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3
*/

func main() {
	str := "pwwkeabcwxyzmnlpq"
	fmt.Println(lengthOfLongestSubstring(str))
}

func lengthOfLongestSubstring(s string) int {
	// 哈希集合，记录每个字符是否出现过
	m := map[byte]int{}
	n := len(s)
	// rp为右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
	// maxLen为最大子串长度
	rp, maxLen := -1, 0
	// i是左指针
	for i := 0; i < n; i++ {
		if i != 0 {
			// 左指针向右移动一格，移除一个字符
			delete(m, s[i-1])
		}
		for rp + 1 < n && m[s[rp+1]] == 0 {
			// 不断地移动右指针
			m[s[rp+1]]++
			rp++
		}
		// 第 i 到 rp 个字符是一个极长的无重复字符子串
		maxLen = max(maxLen, rp - i + 1)
	}
	return maxLen
}

func max(x, y int) int {
	if x < y {
		return y
	}
	return x
}

