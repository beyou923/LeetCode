## 题目描述

给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。找到所有出现两次的元素。

你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？

**示例：**

```shell
输入:
[4,3,2,7,8,2,3,1]
 
输出:
[2,3]
```
来源：力扣（LeetCode）
链接：[https://leetcode-cn.com/problems/find-all-duplicates-in-an-array](https://leetcode-cn.com/problems/find-all-duplicates-in-an-array?fileGuid=rJRtTP9hgkQgpXVW)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

本题同[ LeetCode 448](https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/?fileGuid=rJRtTP9hgkQgpXVW)思路是一样的,都是利用***1 ≤ a[i] ≤ n***这个特性,可以利用数组下标特性标记数字是否出现过。遍历数组中的每个值 a[i] ，如果 a[a[i]] 大于0则取反来表示这个值出现过 ;如果a[i]已经小于0,说明之前已经出现过了,把i + 1加入list即可,最后返回list

## 代码

```java
import java.util.ArrayList;
import java.util.List;
public class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        for(int num : nums){
            int index = Math.abs(num) -1;
            if(nums[index] < 0) // 再次出现nums[index] < 0 说明 index + 1 出现了2次
                result.add(index + 1);
            nums[index] = -nums[index]; // 将 nums[nums[i]]取反
        }
        return result;
    }
}
```
## 复杂度分析

### 时间复杂度:O(n)

### 空间复杂度:O(m)

m为数组中出现2次的元素个数

