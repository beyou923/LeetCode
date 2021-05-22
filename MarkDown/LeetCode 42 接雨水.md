## 题目

给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

示例 1：

![图片](https://uploader.shimo.im/f/PqlL6HmZdJRILlII.png!thumbnail?fileGuid=3qXJD6QJXrP39PhX)

>输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
>输出：6
>解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

**示例 2**：

>输入：height = [4,2,0,3,2,5]
>输出：9

**提示**：

* n == height.length
* 0 <= n <= 3 * 10^4
* 0 <= height[i] <= 10^5

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/trapping-rain-water](https://leetcode-cn.com/problems/trapping-rain-water?fileGuid=3qXJD6QJXrP39PhX)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 方法一：双指针法

从左到右扫描每一列，那么这一列的宽度固定为1，计算该列能装装多少水，最后累加所有列的结果。具体而言，可以看出每一列雨水的高度，取决于，该列 左侧最高的柱子和右侧最高的柱子中最矮的那个柱子的高度。例如求列4的雨水高度，如图：

![图片](https://uploader.shimo.im/f/4O7ev9hzX35UDwgb.png!thumbnail?fileGuid=3qXJD6QJXrP39PhX)

列4 左侧最高的柱子是列3，高度为2（以下用leftHigh表示）。

列4 右侧最高的柱子是列7，高度为3（以下用rightHigh表示）。

列4 柱子的高度为1（以下用height表示）

那么列4的雨水高度为 列3和列7的高度最小值减列4高度，即： min(leftHigh, rightHigh) - height。

列4的雨水高度求出来了，宽度为1，相乘就是列4的雨水体积了。

此时求出了列4的雨水体积。

一样的方法，只要从头遍历一遍所有的列，然后求出每一列雨水的体积，相加之后就是总雨水的体积了。

```java
class Solution {
    public int trap(int[] height) {
        if(height.length < 1) return 0;
        int ans = 0;
        // 从左到右遍历每一列
        // 第一个柱子和最后一个柱子不接雨水
        for(int i = 1; i < height.length - 1; i++) {
            int leftHigh = height[i];
            int rightHigh = height[i];
            // 找左边最高点
            for(int j = i -1; j >= 0; j--){
                leftHigh = Math.max(height[j], leftHigh);
            }
            // 找右边最高点
            for(int j = i + 1; j < height.length; j++){
                rightHigh = Math.max(rightHigh, height[j]);
            }
            // 计算当前列的能装多少水
            ans += Math.min(leftHigh, rightHigh) - height[i];
        }
        return ans;
    }
}
```
时间复杂度：O(n^2)
空间复杂度：O(1)

### 方法二：动态规划

在上面的双指针解法，我们可以看到，只要知道左边柱子的最高高度 和 记录右边柱子的最高高度，就可以计算当前位置的雨水面积，这也是也列来计算的。

即，当前列雨水面积：min(左边柱子的最高高度，记录右边柱子的最高高度) - 当前柱子高度

为了的到两边的最高高度，使用了双指针来遍历，每到一个柱子都向两边遍历一波。

这其实是有重复计算的。

我们把每一个位置的左边最高高度记录在一个数组上（maxLeft），右边最高高度记录在一个数组上（maxRight）。避免的重复计算，者就用到了动态规划。当前位置，左边的最高高度，是前一个位置的最高高度和本高度的最大值。

即从左向右遍历：maxLeft[i] = max(height[i], maxLeft[i - 1]);

从右向左遍历：maxRight[i] = max(height[i], maxRight[i + 1]);

这样就找到递推公式。

```java
class Solution {
    public int trap(int[] height) {
        if(height.length <= 2) return 0;
        int len = height.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        int ans = 0;
        // 从左到右计算每一列左边最大高度
        for(int i = 0; i < len; i++){
            if(i == 0){
                leftMax[i] = height[i];
            }else{
                leftMax[i] = Math.max(height[i], leftMax[i -1]);
            }
        }
        // 从右到左计算每一列右边的最大高度
        for(int i = len - 1; i >= 0; i--){
            if(i == len -1 ){
                rightMax[i] = height[i];
            }else{
                rightMax[i] = Math.max(height[i], rightMax[i + 1]);
            }
        }
        // 累加每一列能装的水量
        for(int i = 1; i < len -1; i++){
            ans += Math.min(rightMax[i], leftMax[i]) - height[i];
        }
        return ans;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(n)

