public class Main {
    public static void main(String args[]) {
        Solution solution = new Solution();
        int[] citations = {2,0,6,1,5};
        int hIndex = solution.hIndex(citations);
        System.out.println(hIndex);
    }

}
