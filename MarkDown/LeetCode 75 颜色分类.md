## 题目

给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

**示例 1**：

>输入：nums = [2,0,2,1,1,0]
>输出：[0,0,1,1,2,2]

**示例 2**：

>输入：nums = [2,0,1]
>输出：[0,1,2]

**示例 3**：

>输入：nums = [0]
>输出：[0]

**示例 4**：

>输入：nums = [1]
>输出：[1]

**提示**：

* n == nums.length
* 1 <= n <= 300
* nums[i] 为 0、1 或 2

**进阶**：

* 你可以不使用代码库中的排序函数来解决这道题吗？
* 你能想出一个仅使用常数空间的一趟扫描算法吗？

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/sort-colors](https://leetcode-cn.com/problems/sort-colors?fileGuid=QXRrqkWPgjqtVyrD)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

### 方法一：快速排序

```java
class Solution {
    public void sortColors(int[] nums) {
        if(nums == null || nums.length < 1) return;
        quickSort(nums, 0, nums.length - 1);
    }
    public void quickSort(int[] nums, int left, int right){
        if(left > right) return;
        int start = left, end = right;
        int key = nums[start];
        while(start < end){
            while(start < end && nums[end] >= key) end--;
            nums[start] = nums[end];
            while(start < end && nums[start] <= key) start++;
            nums[end] = nums[start];
        }
        nums[start] = key;
        quickSort(nums, left, start -1);
        quickSort(nums, start + 1, right);
    }
}
```
时间复杂度：O(nlogn)
空间复杂度：O(1)

### 方法二：单指针法

设置一个头指针ptr指向数组当前头部元素，初始时候ptr=0，经过2次扫描即可。第一次将数组中所有0移动到数组头部，具体而言，遍历到当前元素如果是0，就将其与ptr指向的元素交换，同时ptr加1，这样一趟遍历下来所有0都跑到数组头部去了；第二次将所有1移动到0的后面。具体来说，从ptr开始遍历数组，如果当前元素等于1，将其与ptr指向的元素交换，同时ptr加1

```java
class Solution {
    public void sortColors(int[] nums) {
        int ptr = 0;
        // 将所有的0移动到数组头部
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                int tmp = nums[ptr];
                nums[ptr] = nums[i];
                nums[i] = tmp;
                ptr++; 
            }
        }
        // 将所有1移动到0的后面
        for(int i = ptr; i < nums.length; i++){
            if(nums[i] == 1){
                int tmp = nums[ptr];
                nums[ptr] = nums[i];
                nums[i] = tmp;
                ptr++; 
            }
        }
    }
}
```
时间复杂度：O(n)
空间复杂度：O(1)

### 方法三：双指针法

可以考虑使用指针 p0来交换 0，p2来交换 2。此时，p0的初始值仍然为 0，而 p2 的初始值为 n−1。在遍历的过程中，我们需要找出所有的 0 交换至数组的头部，并且找出所有的 2 交换至数组的尾部。由于此时其中一个指针 p2是从右向左移动的，因此当我们在从左向右遍历整个数组时，如果遍历到的位置超过了p2 ，那么就可以直接停止遍历了。

```java
class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n -1;
        for(int i = 0; i <= p2; i++){
            while(i <= p2 && nums[i] == 2){
                int tmp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = tmp;
                p2--;
            }
            if(nums[i] == 0){
                int tmp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = tmp;
                p0++;
            }
        }
    }
}
```
时间复杂度：O(n)
空间复杂度：O(1)

