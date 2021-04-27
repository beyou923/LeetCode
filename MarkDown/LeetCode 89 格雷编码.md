## 题目

格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。格雷编码序列必须以 0 开头。

**示例 1**:

```shell
输入: 2
输出: [0,1,3,2]
解释:
00 - 0
01 - 1
11 - 3
10 - 2
 
对于给定的 n，其格雷编码序列并不唯一。
例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 
00 - 0
10 - 2
11 - 3
01 - 1
```
**示例 2**:
```shell
输入: 0
输出: [0]
解释: 我们定义格雷编码序列必须以 0 开头。
     给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
     因此，当 n = 0 时，其格雷编码序列为 [0]。
```
来源：力扣（LeetCode）
链接：[https://leetcode-cn.com/problems/gray-code](https://leetcode-cn.com/problems/gray-code?fileGuid=JDKK9rR3gVXyYYCP)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

[直接排列](https://zh.wikipedia.org/wiki/%E6%A0%BC%E9%9B%B7%E7%A0%81?fileGuid=JDKK9rR3gVXyYYCP)：以二进制为0值的格雷码为第零项，第一项改变最右边的位元，第二项改变右起第一个为1的位元的左边位元，第三、四项方法同第一、二项，如此反复，即可排列出n个位元的格雷码。例如n = 3的格雷码排列如下：

![图片](https://uploader.shimo.im/f/Srmgut3oC3tzA4KM.png!thumbnail?fileGuid=JDKK9rR3gVXyYYCP)

上图中二进制与格雷码可以用如下方式转换

```c++
unsigned int binaryToGray(unsigned int num)
{
        return (num >> 1) ^ num;
}
unsigned int grayToBinary(unsigned int num)
{
    unsigned int mask;
    for (mask = num >> 1; mask != 0; mask = mask >> 1)
    {
        num = num ^ mask;
    }
    return num;
}
```
n位元的格雷码一共有2^n个，对应十进制数是[0,2^n - 1]。通过上面的分析可以轻松写出如下代码：
```java
import java.util.ArrayList;
import java.util.List;
class Solution1 {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < Math.pow(2,n); i++){
            result.add((i >> 1) ^ i);
        }
        return result;
    }
}
```
**时间复杂度**：O(2^n)
**空间复杂度**：O(2^n)

