class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n < 1) return;
        int index = nums1.length - 1;
        // 从后往前归并
        while(m > 0 && n > 0){
            if(nums1[m - 1] >= nums2[n - 1]){
                nums1[index--] = nums1[m - 1];
                m--;
            } else{
                nums1[index--] = nums2[n - 1];
                n--;
            }
        }
        // 将nums2中剩余部分顺序插入nums1的前面
        for(int i = 0; i < n; i++){
            nums1[i] = nums2[i];
        }
    }
}
