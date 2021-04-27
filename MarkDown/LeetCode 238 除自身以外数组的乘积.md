## 题目

给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

**示例**:

>输入: [1,2,3,4]
>输出: [24,12,8,6]

**提示**：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。

**说明**: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

**进阶**：

* 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/product-of-array-except-self](https://leetcode-cn.com/problems/product-of-array-except-self?fileGuid=rW36H3gcG9xpkqx9)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 方法一：左右乘积列表

本题和[《剑指offer》第66题](https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof?fileGuid=rW36H3gcG9xpkqx9)一样。分别计算元素i的前缀乘积和后缀乘积，最后再把元素`i`的前缀乘积和后缀乘积相乘即可。元素i的前缀乘积表示从第下标为0的元素开始累乘到下标为`i-1`的元素，注意：下表为0的元素的前缀乘积是1；下标为i的元素的后缀乘积表示从最后一个元素开始累乘到下标为`i + 1`的元素，注意：最后一个元素的后缀乘积是1。

**算法**

1. 初始化两个空数组`mulPre`和`mulSuf`。对于给定索引`i`，`mulPre[i]`代表的是`i`左侧所有数字的乘积，`mulSuf[i]`代表的是`i`右侧所有数字的乘积
2. 我们需要用两个循环来填充`mulPre`和`mulSuf`数组的值。对于数组`mulPre`，`mulPre[0]`应该是 1 ，因为第一个元素的左边没有元素。对于其他元素：`mulPre[i]`=`mulPre[i-1]`*`nums[i-1]`
3. 同理，对于数组`mulSuf`，`mulSuf[length-1]`应为 1。`length`指的是输入数组的大小。其他元素：`mulSuf[i]`=`mulSuf[i+1]`*`nums[i+1]`
4. 当`mulPre`和`mulSuf`数组填充完成 ，我们只需要在输入数组上迭代，且索引`i`处的值为：`mulPre[i] * mulSuf[i]`。
```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        if (len <= 1)
            return nums;
        int[] mulPre = new int[len];
        int[] mutSuf = new int[len];
        int[] result = new int[len];
        // 计算前缀乘积
        for (int i = 0; i < len; i++){
            if (i == 0)
                mulPre[i] = 1;
            else
                mulPre[i] = mulPre[i - 1] * nums[i - 1];
        }
        // 计算后缀乘积
        for (int i = len - 1; i >= 0; i--){
            if (i == len - 1)
                mutSuf[i] = 1;
            else
                mutSuf[i] = nums[i + 1] * mutSuf[i + 1];
        }
        // 填充result
        for (int i = 0; i < len; i++)
            result[i] = mulPre[i] * mutSuf[i];
        return result;
    }
}
**时间复杂度**：O(n)

```
**空间复杂度**：O(n)
### 方法二：空间复杂度为O(1)

由于输出数组不算在空间复杂度内，那么以将`mulPre`或`mulSuf`数组用输出数组来计算。先把输出数组当作`mulPre`数组来计算，然后再动态构造`mulSuf`数组得到结果。让我们来看看基于这个思想的算法。

**算法**

1. 初始化`output`数组，对于给定索引`i`，`output[i]`代表的是`i`左侧所有数字的乘积
2. 构造方式与之前相同，只是我们试图节省空间，先把`output`作为方法一的`mulPre`数组
3. 这种方法的唯一变化就是我们没有构造`mulSuf`数组。而是用一个遍历来跟踪右边元素的乘积。并更新数组`output[i]=output[i] * mulSuf`。然后`mulSuf`更新为`mulSuf *= nums[i]`，其中变量`mulSuf`表示的就是索引右侧数字的乘积。
```java
class Solution {
public int[] productExceptSelf(int[] nums) {
int length = nums.length;
int[] output = new int[length];
// output[i] 表示索引 i 左侧所有元素的乘积
// 因为索引为 '0' 的元素左侧没有元素， 所以 output[0] = 1
for (int i = 0; i < length; i++) {
if(i == 0) {
output[i] = 1;
} else {
output[i] = nums[i - 1] * output[i - 1];
}
}
// mutSuf 为右侧所有元素的乘积
// 刚开始右边没有元素，所以 mutSuf = 1
int mutSuf  = 1;
for (int i = length - 1; i >= 0; i--) {
// 对于索引 i，左边的乘积为 output[i]，右边的乘积为 mutSuf
output[i] = output[i] * mutSuf ;
// mutSuf 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 mutSuf 上
mutSuf  *= nums[i];
}
return output;
}
}
```
**时间复杂度**：O(n)
**空间复杂度**：O(1)

