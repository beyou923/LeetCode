## 题目

请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/daily-temperatures](https://leetcode-cn.com/problems/daily-temperatures?fileGuid=k6rd9Wj3Y86xyRkg)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 方法

### 方法一：暴力法（枚举）

枚举比每一天温度高的天

```java
class Solution {
public int[] dailyTemperatures(int[] T) {
if(T == null) return null;
if(T.length < 2) return new int[]{0};
int[] result = new int[T.length];
for(int i = 0; i < T.length - 1; i++){
boolean flag = false;
// 找到第一个比当前温度高的天
for(int j = i + 1; j < T.length; j++){
if(T[i] < T[j]){
result[i] = j - i;
flag = true;
break;
}
}
// 没有那天比今天温度高
if(flag == false)
result[i] = 0;
}
// 不可能有比最后一天温度高的
result[T.length - 1] = 0;
return result;
}
}
```
时间复杂度：O(n^2)
空间复杂度：O(n)

### 方法二：单调栈

可以维护一个存储下标的单调栈，从栈底到栈顶的下标对应的温度列表中的温度依次递减。如果一个下标在单调栈里，则表示尚未找到下一次温度更高的下标。

正向遍历温度列表。对于温度列表中的每个元素 T[i]，如果栈为空，则直接将 i 进栈，如果栈不为空，则比较栈顶元素 prev 对应的温度 T[prev] 和当前温度 T[i]，如果 T[i] > T[prev]，则将 prev 移除，并将 prev 对应的等待天数赋为 i - prev，重复上述操作直到栈为空或者栈顶元素对应的温度小于等于当前温度，然后将 i 进栈。

```java
class Solution {
    public int[] dailyTemperatures(int[] T) {
        if(T == null ) return null;
        if(T.length < 2) return new int[]{0};
        int len = T.length;
        int[] result = new int[len];
        // 单调栈：栈中元素表示T中气温的下标
        // TODO: 从栈底到栈顶对应对应温度依次上升
        Deque<Integer> stack = new LinkedList<Integer>();
        for(int i = 0; i < len; i++){
            int temp = T[i];
            // 将当前温度与栈顶温度比较
            while(!stack.isEmpty() && temp > T[stack.peek()]){
                int prev = stack.pop();
                result[prev] = i - prev;
            }
            stack.push(i);
        }
        return result;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(n)

