class Solution {
    public boolean backspaceCompare(String s, String t) {
        // 记录 s 和 t 中 # 的数量
        int skipS = 0, skipT = 0;
        int i = s.length() - 1;
        int j = t.length() - 1;

        while(true){
            // 从后向前消除 s 中的 #
            while(i >= 0) {
                if(s.charAt(i) == '#') {
                    skipS++;
                } else {
                    if(skipS > 0) {
                        skipS--;
                    } else {
                        break;
                    }
                }
                i--;
            }
            // 从后向前 消除 t 中的 #
            while (j >= 0) {
                if(t.charAt(j) == '#') {
                    skipT++;
                } else {
                    if(skipT > 0) {
                        skipT--;
                    } else {
                        break;
                    }
                }
                j--;
            }
            // 后半部分 # 消除完了， 接下来比较 s[i] != t[j]
            if(i < 0 || j < 0) break; // s 或者 t遍历到头了
            if(s.charAt(i) != t.charAt(j)) return false;
            i--;
            j--;
        }
        // 说明 s 和 t 同时遍历完毕
        if(i == -1 && j == -1) return true;
        return false;
    }
}
