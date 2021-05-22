## 题目

给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。求在该柱状图中，能够勾勒出来的矩形的最大面积。

![图片](https://uploader.shimo.im/f/wcw0k66VoB2qTCKt.png!thumbnail?fileGuid=66Vp88WWWk9chcTx)

以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。

![图片](https://uploader.shimo.im/f/5T7hoM1tEydljzfI.png!thumbnail?fileGuid=66Vp88WWWk9chcTx)

图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。

**示例**:

>输入: [2,1,5,6,2,3]
>输出: 10
## 思路

### 方法一：枚举宽

确定矩形的左右边界，在左右边界内找一个高度最低的柱状图作为矩形的高，矩形长度为(右边界 - 左边界 + 1),依次枚举以每个柱状图作为左右边界的矩形面积，最后返回一个最大值

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int max = Integer.MIN_VALUE; 
        for(int left = 0; left < heights.length; left++){
            int minHeight = Integer.MAX_VALUE;
            for(int right = left; right < heights.length; right++){
                minHeight = Math.min(minHeight, heights[right]);
                max = Math.max(max, (right - left + 1) * minHeight);
            }
        }
        return max;
    }
}
```
### 方法二：枚举高

使用一重循环枚举某一根柱子，将其固定为矩形的高度 h。随后我们从这跟柱子开始向两侧延伸，直到遇到高度小于 h 的柱子，就确定了矩形的左右边界。如果左右边界之间的宽度为 w，那么对应的面积为 w∗h

```java
class Solution{
    public int largestRectangleArea(int[] heights) {
        int max = Integer.MIN_VALUE;
        int len = heights.length;
        for(int i = 0; i < len; i++){
            int h = heights[i];
            int left = i, right = i;
            while(left - 1 >= 0 && heights[left - 1] >= h)
                left--;
            while(right + 1 < len && heights[right + 1] >= h)
                right++;
            max = Math.max(max, (right - left + 1) * h);
        }
        return max;
    }
}
```
时间复杂度：O(n^2)
空间复杂度：O(1)

这种方法虽然简单，但是会TLE

### 方法三：单调栈

归纳一下枚举「高」的方法：

* 首先我们枚举某一根柱子 ii 作为高 h=heights[i]；
* 随后我们需要进行向左右两边扩展，使得扩展到的柱子的高度均不小于 h。换句话说，我们需要找到左右两侧最近的高度小于 h 的柱子，这样这两根柱子之间（不包括其本身）的所有柱子高度均不小于 h，并且就是 i 能够扩展到的最远范围。

那么我们先来看看如何求出一根柱子的左侧且最近的小于其高度的柱子。除了暴力地进行枚举之外，我们可以通过如下的一个结论来深入地进行思考：

>对于两根柱子 j0以及 j1，如果 j0<j1并且 heights[j0]≥heights[j1]，那么对于任意的在它们之后出现的柱子 i（j1<i），j0一定不会是 i 左侧且最近的小于其高度的柱子。

换句话说，如果有两根柱子 j0和 j1 ，其中 j0 在 j1的左侧，并且 j0的高度大于等于 j1，那么在后面的柱子 ii 向左找小于其高度的柱子时，j1会「挡住」j0 ，j0就不会作为答案了。

```java
public class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        if (heights.length == 1) return heights[0];
        int len = heights.length;
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[len];
        int[] right = new int[len];
        int max = Integer.MIN_VALUE;
        // step 1 寻找以heights[i] 为高的矩形左边界（左边界前一个位置）
        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear(); // 注意要把栈清空
        // step 2 寻找以heights[i] 为高的矩形右边界（右边界后一个位置）
        for(int i = len - 1; i >= 0; i--){
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }
            right[i] = stack.isEmpty() ? len : stack.peek();
            stack.push(i);
        }
        // step 3: 枚举高h，并计算以h为高的句子矩形面积
        for(int i = 0; i < len; i++){
            max = Math.max(max, (right[i] - left[i] - 1) * heights[i]);
        }
        return max;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(n)

