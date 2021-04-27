import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>(){
            public int compare(int[] obj1, int[] obj2){
                if(obj1[0] != obj2[0]){
                    // TODO: 降序就是第二个元素减去第一个元素
                    return obj2[0] - obj1[0];
                } else{
                    // TODO: 升序就是第一个元素减去第二个元素
                    return obj1[1] - obj2[1];
                }
            }
        });
        List<int[]> queue = new ArrayList<>();
        for(int[] person : people){
            // 在list的指定索引位置插入元素，如果该位置已有元素，则该位置之后的元素统统后移一个位置
            // TODO: list的底层实现是链表，插入很高效
            queue.add(person[1],person);
        }
        return queue.toArray(new int[queue.size()][]);
    }
}
