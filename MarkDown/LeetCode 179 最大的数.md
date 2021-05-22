## 题目描述

给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。

**示例 1**：

>输入：nums = [10,2]
>输出："210"

**示例 2**：

>输入：nums = [3,30,34,5,9]
>输出："9534330"

**示例 3**：

>输入：nums = [1]
>输出："1"

**示例 4**：

>输入：nums = [10]
>输出："10"

**提示**：

* 1 <= nums.length <= 100
* 0 <= nums[i] <= 10^9

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/largest-number](https://leetcode-cn.com/problems/largest-number?fileGuid=VyGpXKYW8GprGKh3)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：排序

对于 nums 中的任意两个值 a 和 b，我们无法直接从常规角度上确定其大小/先后关系。但我们可以根据「结果」来决定 a 和 b 的排序关系：

* 如果拼接结果 ab 要比 ba 好，那么我们会认为 a 应该放在 b 前面。
* 否则，那么我们会认为 b 应该放在 a 前面

另外，注意我们需要处理前导零（最多保留一位）。

```java
import java.util.Arrays;
import java.util.Comparator;
class Solution {
    public String largestNumber(int[] nums) {
        if(nums.length < 1) return null;
        int len = nums.length;
        String[] strNums = new String[len];
        for(int i = 0; i < len; i++) strNums[i] = "" + nums[i];
        // 按字符降序排序
        Arrays.sort(strNums,new Comparator<String>(){
            public int compare(String a, String b){
                // 如果拼接结果 ab 要比 ba 好，那么我们会认为 a 应该放在 b 前面
                // 否则，那么我们会认为 b 应该放在 a 前面
                String sa = a + b;
                String sb = b + a;
                return sb.compareTo(sa);
            }
        });
        StringBuffer sb = new StringBuffer();
        for(String str : strNums) sb.append(str);
        int index = 0;
        // 需要处理前导零（最多保留一位）
        while(index < sb.length() - 1 && sb.charAt(index) == '0') index++;
        return sb.substring(index);
    }
}
```
时间复杂度：O(n^2) ,由于需要对插入的字符串排序，在java中对非基本类型排序不会使用快速排序，Arrays.sort() 的底层实现会根据「元素数量/元素是否大致有序」决定是使用插入排序还是归并排序，这里假设使用插入排序，则时间复杂度是O(n^2)
空间复杂度：O(n)

