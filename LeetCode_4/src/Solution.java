public class Solution {
    // 归并排序
    public static double findMedianSortedArrays(int[] nums1, int[] nums2){
        int m = nums1.length, n = nums2.length;
        if (m < 1) return findMedian(nums2);
        if (n < 1) return findMedian(nums1);
        int[] merge = new int[m + n];
        int i = 0, j = 0, index = 0;
        while (i < m && j < n){
            if (nums1[i] <= nums2[j])
                merge[index++] = nums1[i++];
            else
                merge[index++] = nums2[j++];
        }
        if (i == m) {
            while (j < n)
                merge[index++] = nums2[j++];
        }
        if (j == n) {
            while (i < m)
                merge[index++] = nums1[i++];
        }
        return findMedian(merge);
    }
    // 返回有序数组的中位数
    public static double findMedian(int[] nums){
        int len = nums.length;
        int mid = len / 2;
        double media = 0;
        if (len % 2 == 0)
            media = (nums[mid] + nums[mid - 1]) / 2.0;
        else
            media = nums[mid];
        return media;
    }

    public static void main(String[] args) {
        int[] num1 = new int[]{1,2};
        int[] num2 = new int[]{3,4};
        System.out.println(findMedianSortedArrays(num1,num2));;
    }
}
