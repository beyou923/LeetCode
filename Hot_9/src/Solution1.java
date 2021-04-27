public class Solution1 {
    public boolean isPalindrome(int x) {
        String str = Integer.toString(x);
        int len = str.length();
        int i = 0, j = len - 1;
        while (i <= j){
            if (str.charAt(i) == str.charAt(j)){
                i++;
                j--;
                continue;
            } else
                return false;
        }
        return true;
    }
}
