class Solution {
    public static int reverse(int x) {
        int result = 0;
        while(x != 0){
            int end = x % 10;
            if(Math.abs(result) > Integer.MAX_VALUE / 10)
                return 0;
            if (Math.abs(result) == Integer.MAX_VALUE / 10 && (end > 7 || end < -8))
                return 0;
            result = result * 10 + end;
            x = x / 10;
        }
        return result;
    }
    public static void main(String[] args){
        int x = -25;
        int res = reverse(x);
        System.out.println(res);
    }
}
