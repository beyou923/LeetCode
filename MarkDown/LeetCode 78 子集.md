## 题目

给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。解集不能包含重复的子集。你可以按 任意顺序 返回解集。

**示例 1**：

>输入：nums = [1,2,3]
>输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

**示例 2**：

>输入：nums = [0]
>输出：[[],[0]]

**提示**：

* 1 <= nums.length <= 10
* -10 <= nums[i] <= 10
* nums 中的所有元素 互不相同

来源：力扣（LeetCode）

链接：[https://leetcode-cn.com/problems/subsets](https://leetcode-cn.com/problems/subsets?fileGuid=cdD6X3yhdwtPPXKx)

著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

## 思路

求子集问题和[回溯算法：求组合问题！](https://mp.weixin.qq.com/s?__biz=MzUxNjY5NTYxNA==&mid=2247485253&idx=1&sn=8332edaabc9bf43e45835bce7964ce88&scene=21#wechat_redirect&fileGuid=cdD6X3yhdwtPPXKx)和[回溯算法：分割问题！](https://mp.weixin.qq.com/s?__biz=MzUxNjY5NTYxNA==&mid=2247485372&idx=1&sn=29cc3421fb742faa57824b9a626342ad&scene=21#wechat_redirect&fileGuid=cdD6X3yhdwtPPXKx)又不一样了。如果把 子集问题、组合问题、分割问题都抽象为一棵树的话，那么组合问题和分割问题都是收集树的叶子节点，而子集问题是找树的所有节点！其实子集也是一种组合问题，因为它的集合是无序的，子集{1,2} 和 子集{2,1}是一样的。那么既然是无序，取过的元素不会重复取，写回溯算法的时候，for就要从startIndex开始，而不是从0开始！

什么时候for可以从0开始呢？求排列问题的时候，就要从0开始，因为集合是有序的，{1, 2} 和{2, 1}是两个集合，排列问题我们后续的文章就会讲到的。

以示例中nums = [1,2,3]为例把求子集抽象为树型结构，如下：

![图片](https://uploader.shimo.im/f/nZstNwFdFzRyQIoY.png!thumbnail?fileGuid=cdD6X3yhdwtPPXKx)

从图中红线部分，可以看出遍历这个树的时候，把所有节点都记录下来，就是要求的子集集合。

```java
import java.util.ArrayList;
import java.util.List;
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0);
        return result;
    }
    public void dfs(int[] nums, int startIndex){
        result.add(new ArrayList<>(path));
        if(startIndex >= nums.length) // 可以不要这个判断
            return;
        for(int i = startIndex; i < nums.length; i++){
            path.add(nums[i]);
            dfs(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
```
在注释中，可以发现可以不写终止条件，因为本来我们就要遍历整颗树。不用担心担心不写终止条件会不会无限递归？并不会，因为每次递归的下一层就是从i+1开始的。
## 参考

[https://mp.weixin.qq.com/s/NNRzX-vJ_pjK4qxohd_LtA](https://mp.weixin.qq.com/s/NNRzX-vJ_pjK4qxohd_LtA?fileGuid=cdD6X3yhdwtPPXKx)

