## 题目描述

给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。找到所有在 [1, n] 范围之间没有出现在数组中的数字。

您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

**示例:**

```shell
输入:
[4,3,2,7,8,2,3,1]
输出:
[5,6]
```
来源：力扣（LeetCode）
链接：[https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array](https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array?fileGuid=CvDQ8h6D3PRX66cQ)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

题干中说***1 ≤ a[i] ≤ n***，可以利用数组下标特性标记数字是否出现过。遍历数组中的每个值 a[i] ，如果 a[a[i]] 大于0则取反来表示这个值出现过 ;再遍历一次数组如果a[i] 大于0说明 i + 1没有出现.

## 代码

```java
import java.util.ArrayList;
import java.util.List;
public class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for(int num : nums){
            int index = Math.abs(num) -1;
            if(nums[index] > 0)
                nums[index] = -nums[index]; // 表示 index + 1 这个数出现了
        }
        List<Integer> result = new ArrayList<Integer>();
        for(int index = 0; index < nums.length; index++){
            if(nums[index] > 0)
                result.add(index + 1); // index + 1 没出现过
        }
        return result;
    }
}
```
## 复杂度分析

### 时间复杂度:O(n)

### 空间复杂度:O(1)

