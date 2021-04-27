import java.util.Scanner;

public class Main {
    public static int func(String s, String t, int i, int j){
        int max = t.length() - j;
        for (int si = i; si < s.length(); si++){
            int ti = j;
            for (ti = j; ti < t.length(); ti++){
                if (s.charAt(si) < t.charAt(ti)){
                    break;
                }
            }
            if (ti != t.length()){
                int len = s.length() - si + t.length() - ti;
                if (max < len) max = len;
            }
        }
        return max;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String t = sc.nextLine();
        int q = sc.nextInt();
//        System.out.println(s + " " + t + " " + q);
        // 输入q对数据
        int[][] index = new int[q][2];
        for (int i = 0; i < q; i++){
            index[i][0] = sc.nextInt();
            index[i][1] = sc.nextInt();
//            System.out.println(index[i][0] + " " + index[i][1]);
        }
        for (int row = 0; row < index.length; row++){
//            System.out.println(index[row][0] + " " + index[row][1]);
            System.out.println(func(s,t,index[row][0],index[row][1]));
        }
    }
}
