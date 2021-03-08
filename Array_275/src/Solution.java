/*
* 给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照升序排列。编写一个方法，计算出研究者的 h 指数。
* h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。
* （其余的N - h篇论文每篇被引用次数不多于 h 次。）"
*
* 示例:
*   输入: citations = [0,1,3,5,6]
*   输出: 3
*   解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
*   由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
*
* 说明:
*   如果 h 有多有种可能的值 ，h 指数是其中最大的那个。
链接：https://leetcode-cn.com/problems/h-index-ii
* */
public class Solution {
    // 线性扫描法
    public int hIndex(int[] citations) {
        int i = 0;
        int n = citations.length;
        // 逆序扫描
        while (i < n && citations[n-1-i] > i ){
            i++;
        }
        return i;
    }

    // 二分法
    public int hIndex1(int[] citations) {
        int len = citations.length,start = 0, end = len -1;
        while (start <= end) {
            // 必须放在循环内部  下面两种写法等价
//            int mid = (start + end) / 2;
            int mid = start + (end - start) / 2;
            if (citations[mid] == len - mid)
                return len - mid;
            // 搜索右边子表 [mid+1 ： end]
            else if (citations[mid] < len - mid)
                start = mid + 1;
            // 搜索左边子表 [start : mid -1]
            else
                end = mid -1;
        }
        return len - start;
    }
}
