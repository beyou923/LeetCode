## 题目

给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

**示例 1**:

>输入: [2,3,-2,4]
>输出: 6
>解释: 子数组 [2,3] 有最大乘积 6。

**示例 2**:

>输入: [-2,0,-1]
>输出: 0
>解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/maximum-product-subarray](https://leetcode-cn.com/problems/maximum-product-subarray?fileGuid=VrqcxdVrDyHTCjCj)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路:动态规划

这道题改编自[LeetCode 53](https://leetcode-cn.com/problems/maximum-subarray/?fileGuid=VrqcxdVrDyHTCjCj)题，也是用动态规划解，而且需要用两个 dp 数组，其中 f[i] 表示子数组 [0, i] 范围内并且一定包含 nums[i] 数字的最大子数组乘积，g[i] 表示子数组 [0, i] 范围内并且一定包含 nums[i] 数字的最小子数组乘积，初始化时 f[0] 和 g[0] 都初始化为 nums[0]，其余都初始化为0。那么从数组的第二个数字开始遍历，那么此时的最大值和最小值只会在这三个数字之间产生，即 f[i-1]*nums[i]，g[i-1]*nums[i]，和 nums[i]。所以用三者中的最大值来更新 f[i]，用最小值来更新 g[i]，然后用 f[i] 来更新结果 res 即可，由于最终的结果不一定会包括 nums[n-1] 这个数字，所以 f[n-1] 不一定是最终解，不断更新的结果 res 才是

```java
public class Solution {
    public int maxProduct(int[] nums) {
        int[] f = new int[nums.length];
        int[] g = new int[nums.length];
        int result = nums[0];
        f[0] = nums[0];
        g[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            f[i] = Integer.max(Integer.max(f[i-1] * nums[i], g[i-1] * nums[i]), nums[i]);
            g[i] = Integer.min(Integer.min(f[i-1] * nums[i], g[i-1] * nums[i]), nums[i]);
            if(f[i] > result)
                result = f[i];
        }
        return result;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(n)

