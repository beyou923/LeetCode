class Solution {
    public int mySqrt(int x) {
        if(x == 0) return 0;
        if(x == 1) return 1;
        int start = 1, end = x / 2, ans = 0;
        while(start <= end){
            int mid = start + (end - start) / 2;
            // mid有可能成为x的平方根
            if((long)mid * mid <= x) {
                ans = mid;
                start = mid + 1;
            } else end = mid - 1;
        }
        return ans;
    }
}
