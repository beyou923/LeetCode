## 题目

给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。

**示例 1**：

>输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
>输出：[1,2,2,3,5,6]

**示例 2**：

>输入：nums1 = [1], m = 1, nums2 = [], n = 0
>输出：[1]

**提示**：

* nums1.length == m + n
* nums2.length == n
* 0 <= m, n <= 200
* 1 <= m + n <= 200
* -10^9 <= nums1[i], nums2[i] <= 10^9

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/merge-sorted-array](https://leetcode-cn.com/problems/merge-sorted-array?fileGuid=Xqy3PWcGcCjVJJwV)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

对两个有序数组从后往前归并，将较大者插入nums1的末尾

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n < 1) return;
        int index = nums1.length - 1;
        // 从后往前归并
        while(m > 0 && n > 0){
            if(nums1[m - 1] >= nums2[n - 1]){
                nums1[index--] = nums1[m - 1];
                m--;
            } else{
                nums1[index--] = nums2[n - 1];
                n--;
            }
        }
        // 将nums2中剩余部分顺序插入nums1的前面
        for(int i = 0; i < n; i++){
            nums1[i] = nums2[i];
        }
    }
}
```
**时间复杂度**：O(m + n)
**空间复杂度**：O(1)

