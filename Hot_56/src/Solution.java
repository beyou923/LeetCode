import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length < 1) return new int[0][0];

        // 对 intervals中的区间按照左断点排序
        Arrays.sort(intervals,new Comparator<int[]>(){
            public int compare(int[] comparator1, int[] comparator2){
                return comparator1[0] - comparator2[0];
            }
        });
        List<int[]> merged = new ArrayList<>();
        for(int i = 0; i < intervals.length; i++){
            // 记录区间左右端点
            int start = intervals[i][0], end = intervals[i][1];
            // 直接插入
            if(merged.size() == 0 || merged.get(merged.size() - 1)[1] < start){
                merged.add(intervals[i]);
            } else {
                // 保证每一段区间右端点最大
                merged.get(merged.size() - 1)[1] = Math.max(end, merged.get(merged.size() - 1)[1]);
            }
        }
        return merged.toArray(new int[merged.size()][2]);
    }
}

