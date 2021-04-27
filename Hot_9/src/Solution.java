class Solution {
    public boolean isPalindrome(int x) {
        int tmp = x,result = 0;
        if(x < 0) return false;
        while(x != 0){
            int end = x % 10;
            if(Math.abs(result) > Integer.MAX_VALUE / 10)
                return false;
            if(Math.abs(result) == Integer.MAX_VALUE / 10 && end > 7 )
                return false;
            result = result * 10 + end;
            x = x / 10;
        }
        return tmp == result;

    }
}
