## 题目

给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

**示例 1**:

>输入:nums = [1,1,1], k = 2
>输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。

**说明**:

1. 数组的长度为 [1, 20,000]。
2. 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/subarray-sum-equals-k](https://leetcode-cn.com/problems/subarray-sum-equals-k?fileGuid=gpjwXKrpJwxpwP6Q)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 方法一：暴力破解（枚举法）

```java
class Solution {
    int count = 0;
    public int subarraySum(int[] nums, int k) {
        for(int start = 0; start < nums.length; start++){
            int sum = 0;
            sum += nums[start];
            if(sum == k) count++;
            for(int i = start + 1; i < nums.length; i++){
                sum += nums[i];
                if(sum == k) count++;
            }
        }
        return count;
    }
}
```
时间复杂度：O(n^2)
空间复杂度：O(1)

### 方法二：哈希表 + 前缀和

我们定义pre[i] 为[0..i] 里所有数的和，则pre[i]可以由pre[i−1] 递推而来，即：

pre[i]=pre[i−1]+nums[i]

那么[j..i]这个子数组和为k 这个条件我们可以转化为

pre[i]−pre[j−1]==k

简单移项可得符合条件的下标 j 需要满足

pre[j−1]==pre[i]−k

所以我们考虑以 i 结尾的和为 k 的连续子数组个数时只要统计有多少个前缀和为pre[i]−k 的 pre[j] 即可。我们建立哈希表map，以和为键，出现次数为对应的值，记录pre[i] 出现的次数，从左往右边更新map 边计算答案，那么以 i 结尾的答案 map[pre[i]−k] 即可在 O(1) 时间内得到。最后的答案即为所有下标结尾的和为 k 的子数组个数之和。

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        int pre = 0 , count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        for(int i = 0; i < nums.length; i++){
            pre += nums[i]; // 计算以i结尾的前缀和
            if(map.containsKey(pre - k)){ 
                count += map.get(pre - k);
            }
            map.put(pre , map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
```
**时间复杂度**：O(n)
**空间复杂度**：O(n)

