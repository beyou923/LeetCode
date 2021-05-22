## 题目

在一排树中，第 i 棵树产生 tree[i] 型的水果。

你可以从你选择的任何树开始，然后重复执行以下步骤：

* 把这棵树上的水果放进你的篮子里。如果你做不到，就停下来。
* 移动到当前树右侧的下一棵树。如果右边没有树，就停下来。

请注意，在选择一颗树后，你没有任何选择：你必须执行步骤 1，然后执行步骤 2，然后返回步骤 1，然后执行步骤 2，依此类推，直至停止。

你有两个篮子，每个篮子可以携带任何数量的水果，但你希望每个篮子只携带一种类型的水果。

用这个程序你能收集的水果树的最大总量是多少？

**示例 1**：

>输入：[1,2,1]
>输出：3
>解释：我们可以收集 [1,2,1]。

**示例 2**：

>输入：[0,1,2,2]
>输出：3
>解释：我们可以收集 [1,2,2]
>如果我们从第一棵树开始，我们将只能收集到 [0, 1]。

**示例 3**：

>输入：[1,2,3,2,2]
>输出：4
>解释：我们可以收集 [2,3,2,2]
>如果我们从第一棵树开始，我们将只能收集到 [1, 2]。

**示例 4**：

>输入：[3,3,3,1,2,1,1,2,3,3,4]
>输出：5
>解释：我们可以收集 [1,2,1,1,2]
>如果我们从第一棵树或第八棵树开始，我们将只能收集到 4 棵水果树。

**提示**：

* 1 <= tree.length <= 40000
* 0 <= tree[i] < tree.length

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/fruit-into-baskets](https://leetcode-cn.com/problems/fruit-into-baskets?fileGuid=3rPgkpYttCtV3qgX)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路：滑动窗口

### 方法一：朴素实现

定义一个哈希map<key,value>，key表示元素值，value表示这个元素在滑动窗口中出现的次数，用滑动窗口在数组上滑动，初始时滑动的左右边界（left和right）都指向数组第一个元素，用滑动窗口在数组上滑动，

* 如果map中k-v对数小于2，直接插入元素，并记录元素出现次数，同时right向右移动；
* 如果map中k-v对数等于2且当前元素已在map中出现过，直接插入元素，并记录元素出现次数，同时right向右移动；
* 不能再向map中加入第三个元素时，统计滑动窗口的长度，具体而言：
    * 将用滑动左右边界计算窗口的长度 right - left，并与上一次的窗口大小比较，选择较大者作为结果 maxLen = max(maxLen, right - left)
    * 将滑动窗口左边界滑出区，left++，同时左边界元素在map中的次数减一，如果滑出窗口元素在map的次数减为0，则从map中移除
```java
class Solution {
    public int totalFruit(int[] tree) {
        int len = tree.length;
        if(len == 1) return 1;
        int left = 0, right = 0;
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while(right < len) {
            // 将尽可能多的元素加入窗口
            while (right < len && map.size() == 2 && map.containsKey(tree[right])){
                map.put(tree[right], map.getOrDefault(tree[right], 0) + 1);
                right++;
            }
             // 窗口已不能再添加元素，统计窗口大小，每次将最左端元素滑出窗口
            while(map.size() == 2){  
                int outofWindow = tree[left];      
                if(map.get(outofWindow) != 0) {
                   maxLen = Math.max(maxLen, right - left);
                   map.put(outofWindow, map.get(outofWindow) - 1);
                   left++;
                   if(map.get(outofWindow) == 0) {
                       map.remove(outofWindow);
                   }
               }
           }
           if(right == len) {
               break;
           }
           // 向滑动窗口中加入元素
           map.put(tree[right], map.getOrDefault(tree[right], 0) + 1);
           right++;
        }
        return Math.max(maxLen, right - left);
    }
}
```
时间复杂度：O(n^2)
空间复杂度：O(n)

这种方法在统计窗口长度时候，会重复遍历窗口中的元素，效率不高

### 方法二：优化版

这种方法主要时优化了计算窗口长度操作，其次将空间复杂度降为O(1)

```java
class Solution {
    public int totalFruit(int[] tree) {
        int maxLen = 0, len = tree.length;
        int start = 0, end = 0;
        int one = tree[end], two = 0;
        // 找第一种果树
        while(end < len && tree[end] == one) end++;
        if(end == len) return len;
        // 找第二种果树
        two = tree[end++];
        // 计算滑动窗口长度
        for(; end < len; end++){
            if(tree[end] != one && tree[end] != two){
                // 计算当前滑动窗口的长度
                maxLen = Math.max(maxLen, end - start);
                // 更新两种果树类型
                one = tree[end - 1];
                two = tree[end];
                // 更新滑动窗口的左边界
                start = end - 1;
                // 尽量向左延伸滑动窗口的左边界
                while(tree[start - 1] == one)
                    start--;
            }
        }
        return Math.max(maxLen, end - start);
    }
}
```
时间复杂度：O(n)
空间复杂度：O(1)

