import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
       int []array = {0,1,2,5,6};
        int h = solution.hIndex1(array);
        System.out.println(h);
    }
}
