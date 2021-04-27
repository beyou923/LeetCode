class Solution {
    public int hammingDistance(int x, int y) {
        int distance = 0;
        int xor = x ^ y; // 按位异或或xor的二进制表示中1的个数就是汉明距离
        while(xor != 0){ // 求出xor二进制表示中1的个数
            int and = xor & 0x1;
            if(and == 1) distance++;
            xor = xor >> 1;
        }
        return distance;
    }
}
