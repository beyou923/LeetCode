## 题目

给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。

**示例 1**:

>输入: 2
>输出: [0,1,1]

**示例 2**:

>输入: 5
>输出: [0,1,1,2,1,2]

**进阶**:

* 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
* 要求算法的空间复杂度为O(n)。
* 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/counting-bits](https://leetcode-cn.com/problems/counting-bits?fileGuid=yTPkgY9RydhDCRPD)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：动态规划

确定dp数组机器下标含义

dp[i]表示十进制数i的二进制位表示中1的位数

确定递推公式

当计算 i 的二进制表示中1的位数时，如果存在 0≤j<i，j 的二进制表示中1的位数dp[j]已知，且 i 和 j 相比，i 的二进制表示只多了一个 1，则可以快速得到 i 的二进制表示中1的位数为dp[i] = dp[j] + 1

对于正整数 x，如果可以知道最大的正整数 y，使得 y≤x 且 y 是 2 的整数次幂，则 y 的二进制表示中只有最高位是 1，其余都是 0，此时称 y 为 x 的「最高有效位」。令z=x−y，显然0≤z<x，则 dp[x] = dp[z] +1=dp[x-y] + 1

相当于用2^p（1<=p，其中，2^p<= num），对[0,num]之间的数分组，将2^p看成是y，[2^p,2^(p+1))之间看成一组，里面的数看成是x，z = x - y

### 简单版

这个版本更容易理解

```java
class Solution {
    public int[] countBits(int num) {
        if(num == 0) return new int[]{0};
        if(num == 1) return new int[]{0,1};
        if(num == 2) return new int[]{0,1,1};
        int[] dp = new int[num + 1];
        dp[0] = 0;
        dp[1] = 1;
        int i = 1;
        int y = (int)Math.pow(2,i);
        for(int x = 2; x <= num && x <= (int)Math.pow(2,i + 1); x++){
            if(x == y) dp[x] = 1;
            if(x == (int)Math.pow(2,i + 1)){
                dp[x] = 1;
                i++;
                y = (int)Math.pow(2,i);
            }
            dp[x] = dp[x - y] + 1;
        }
        return dp;
    }
}
```
### 优化版

```java
class Solution {
    public int[] countBits(int num) {
        
        int[] dp = new int[num + 1];
        int highBit = 0;
        for(int i = 1; i <= num; i++){
            // 判断i是不是2的整数次幂
            if((i & (i -1)) == 0){
                highBit = i;
            }
            dp[i] = dp[i - highBit] + 1;
        }
        return dp;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(n)

