import java.util.HashSet;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        String[] line = sc.nextLine().split(" ");
        int[] weight = new int[n];
        // 输入果子重量
        for (int i = 0 ; i < n; i++){
            weight[i] = Integer.parseInt(line[i]);
        }
        // 输入问题
        int[] question = new int[m];
        for (int i = 0; i < m; i++){
            question[i] = sc.nextInt();
        }
        Solution s = new Solution();
        s.func(weight,question);
    }
    public void func(int[] weight, int[] question){
        HashSet<Integer> set = new HashSet<>();
        for (int num : weight){
            set.add(num);
        }
        for (int q : question){
            boolean flag= false;
            for (int num : weight){
                int target = num - q;
                boolean c = set.contains(target);
                if (c){
                    System.out.println(target + " " + num);
                    flag = true;
                    break;
                }
            }
            if (false == flag){
                System.out.println(-1 + " " + -1);
            }
        }
    }
}
