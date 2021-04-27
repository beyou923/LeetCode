import java.util.ArrayList;
import java.util.List;

class Solution1 {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < Math.pow(2,n); i++){
            result.add((i >> 1) ^ i);
        }
        return result;
    }
}
