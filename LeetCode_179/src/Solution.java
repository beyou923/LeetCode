import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String largestNumber(int[] nums) {
        if(nums.length < 1) return null;
        int len = nums.length;
        String[] strNums = new String[len];
        for(int i = 0; i < len; i++) strNums[i] = "" + nums[i];
        // 按字符降序排序
        Arrays.sort(strNums,new Comparator<String>(){
            public int compare(String a, String b){
                // 如果拼接结果 ab 要比 ba 好，那么我们会认为 a 应该放在 b 前面
                // 否则，那么我们会认为 b 应该放在 a 前面
                String sa = a + b;
                String sb = b + a;
                return sb.compareTo(sa);
            }
        });
        StringBuffer sb = new StringBuffer();
        for(String str : strNums) sb.append(str);
        int index = 0;
        // 需要处理前导零（最多保留一位）
        while(index < sb.length() - 1 && sb.charAt(index) == '0') index++;

        return sb.substring(index);
    }
    public static void main(String[] args){
        int a = 1;
        System.out.println(a % 3);

    }
}
