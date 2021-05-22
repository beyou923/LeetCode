## 题目

给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。

**示例 1**：

>输入：nums = [-4,-1,0,3,10]
>输出：[0,1,9,16,100]
>解释：平方后，数组变为 [16,1,0,9,100]
>排序后，数组变为 [0,1,9,16,100]

**示例 2**：

>输入：nums = [-7,-3,2,3,11]
>输出：[4,9,9,49,121]

**提示**：

* 1 <= nums.length <= 10^4
* -10^4 <= nums[i] <= 10^4
* nums 已按 非递减顺序 排序



进阶：

* 请你设计时间复杂度为 O(n) 的算法解决本问题

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/squares-of-a-sorted-array](https://leetcode-cn.com/problems/squares-of-a-sorted-array?fileGuid=wy36WvRcDytxJwRX)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：双指针法

由于数组元素按照非递减（有可能有重复元素）排序，通过一趟扫描找到数组中绝对值最小的元素的位置，绝对值最小的元素按平方一定是新数组的第一个元素。定义两个指针p和q分别指向nums数组绝对值最小的元素左边和右边元素，每次从p和q中选出绝对值较小者计算平方并加入新数组：

* 如果 abs(nums[p] <= abs(nums[q])) 则

ans[index++] = nums[p] * nums[p]; p--;

* 如果 abs(nums[p] > abs(nums[q])) 则

ans[index++] = nums[q] * nums[q]; q++;

时间复杂度：O(n)

空间复杂度：O(n)

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        if(len == 1) return new int[]{nums[0] * nums[0]};
        int mid = -1;
        int pre = Integer.MAX_VALUE;
        for(int i = 0; i< len; i++){
            if(Math.abs(nums[i]) < Math.abs(pre)){
                mid = i;
                pre = nums[i];
            }
        }
        
       return func(nums, mid, len);
        
    }
    
    public int[] func(int[] nums, int mid, int len){
        int[] ans = new int[len];
        ans[0] = nums[mid] * nums[mid];
        int left = mid - 1, right = mid + 1;
        int index = 1;
        while(left >= 0 && right < len){
            if(Math.abs(nums[left]) <= Math.abs(nums[right])) {
                ans[index++] = nums[left] * nums[left];
                left--;
            } else {
                ans[index++] = nums[right] * nums[right];
                right++;
            }
        }
        while(left >= 0){
            ans[index++] = nums[left] * nums[left];
            left--;
        }
        while(right < len) {
            ans[index++] = nums[right] * nums[right];
            right++;
        }
        return ans;
    }
}
```
