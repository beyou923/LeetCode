package main

import "fmt"

/*
	给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000
	输入: "babad"
	输出: "bab"
	注意: "aba" 也是一个有效答案。
	https://leetcode-cn.com/problems/longest-palindromic-substring/
*/

//动态规划法
//状态转移方程  dp[i][j] = (s[i]==s[j]) AND (j-i < 3 OR dp[i+1][j-1])
func longestPalindrome(s string) string {
	n := len(s)
	//长度小于2肯定是回文
	if n <2 {
		return s
	}
	//begin和maxLen分别记录回文子串的左右边界
	begin, maxLen := 0, 1
	// dp[i][j]表示s[i:j]是否是回文
	dp := make([][]bool, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]bool, n)
	}
	//注意：左下角先填 i、j分别是左右边界 i <= j
	//从第一列开始填
	for j := 1; j < n; j++ {
		for i := 0; i  < j; i++ {
			if s[i] != s[j] { // 两头的字符不同肯定不是回文
				dp[i][j] = false
			} else {
				if j - i < 3 { //子串长度小于3 不用判断除去两头字符后的子串是否是回文
					dp[i][j] = true
				} else {
					dp[i][j] = dp[i+1][j-1]
				}
			}
			if dp[i][j] && j-i+1 > maxLen{
				maxLen = j-i+1
				begin = i
			}
		}
	}
	return s[begin:begin + maxLen]
}

func main() {
	fmt.Println(longestPalindrome("aabccbaa"))
}