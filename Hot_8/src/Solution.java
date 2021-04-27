
class Solution {
    public int myAtoi(String s) {
        if(s.isEmpty()) return 0;
        int result = 0;
        int sgn = 1; // 符号位标志，1表示正数，-1表示负数
        int i = 0, len = s.length();
        // 忽略前面的空格
        while(i < len && s.charAt(i) == ' ') i++;
        // 找正负号
        if(i < len){
            if(s.charAt(i) == '+'){
                sgn = 1;
                i++;
            }else if(s.charAt(i) == '-'){
                sgn = -1;
                i++;
            }
        }
        // 找数字
        while(i < len && s.charAt(i) >= '0' && s.charAt(i) <= '9'){
            int end = s.charAt(i) - '0';
            if(Math.abs(result) > Integer.MAX_VALUE / 10){
                result = sgn == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                break;
            }

            if(Math.abs(result) == Integer.MAX_VALUE / 10){
                if(sgn == 1 && end > 7){
                    result = Integer.MAX_VALUE;
                    break;
                }

                if(sgn == -1 && end > 8){
                    result = Integer.MIN_VALUE;
                    break;
                }

            }

            result = result * 10 + end;
            i++;
        }
        return result * sgn;
    }
}






