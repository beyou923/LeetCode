package main

import (
	"fmt"
	"strconv"
)

/*
	你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：
	你写出一个秘密数字，并请朋友猜这个数字是多少。
	朋友每猜测一次，你就会给他一个提示，告诉他的猜测数字中有多少位属于数字和确切位置都猜对了（称为“Bulls”, 公牛），有多少位属于数字猜对了但是位置不对（称为“Cows”, 奶牛）。
	朋友根据提示继续猜，直到猜出秘密数字。
	请写出一个根据秘密数字和朋友的猜测数返回提示的函数，返回字符串的格式为 xAyB ，x 和 y 都是数字，A 表示公牛，用B表示奶牛。
	你可以假设秘密数字和朋友的猜测数都只包含数字，并且它们的长度永远相等。
	xA 表示有 x 位数字出现在秘密数字中，且位置都与秘密数字一致。
	yB 表示有 y 位数字出现在秘密数字中，但位置与秘密数字不一致。
	请注意秘密数字和朋友的猜测数都可能含有重复数字，每位数字只能统计一次。
		输入: secret = "1807", guess = "7810"
		输出: "1A3B"
		解释: 1公牛和3奶牛。公牛是 8，奶牛是 0, 1和 7。

	链接：https://leetcode-cn.com/problems/bulls-and-cows
*/

func getHint(secret string, guess string) string {
	n := len(secret)
	// 分别存储 secret 和 guess 某个数字出现的次数
	secretMap,guessMap := make(map[byte]int,n),make(map[byte]int,n)
	// count : 相同数字出现的总次数 bulls: 位置也相同的数字出现次数
	count, bulls := 0,0
	for i := 0; i < n; i++ {
		// 相同位置数字也相同
		if secret[i] == guess[i] {
			bulls++
		}
		// 没有就加入map
		if _,ok := secretMap[secret[i]]; ok == false {
			secretMap[secret[i]] = 1
		} else { // 存在就自增
			secretMap[secret[i]] ++
		}
		if _,ok := guessMap[guess[i]]; ok == false{
			guessMap[guess[i]] = 1
		} else {
			guessMap[guess[i]] ++
		}

	}
	// 统计相同字符出现的总次数
	for key,value := range secretMap{
		if v,ok := guessMap[key]; ok {
			if value <= v {
				count = count + value
			} else {
				count = count + v
			}
		}
	}
	return strconv.Itoa(bulls) + "A" + strconv.Itoa(count - bulls) + "B"
}

func main() {
	secret := "1123"
	guess := "0111"
	fmt.Println(getHint(secret,guess))
}