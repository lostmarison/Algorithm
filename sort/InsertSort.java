package sort;

public class InsertSort {
    /**
     * 插入排序算法实现
     *
     * @param nums 待排序的整型数组
     */
    public void insertSort(int[] nums) {
        int n = nums.length; // 获取数组的长度
        int i, j, temp; // 声明循环变量i, j和临时变量temp
        // 外层循环：遍历数组中的每一个元素（从第二个元素开始）
        for (i = 1; i < n; i++) {
            temp = nums[i]; // 将当前元素保存到临时变量temp中
            // 内层循环：在已排序的序列中从后向前扫描，找到当前元素应该插入的位置
            for (j = i - 1; j >= 0 && temp < nums[j]; j--) {
                nums[j + 1] = nums[j]; // 将大于当前元素（temp）的元素都向后移动一位
            }
            // 当内层循环结束时，j+1的位置就是当前元素应该插入的位置
            nums[j + 1] = temp; // 将当前元素插入到正确的位置
        }
    }

    public static void main(String[] args) {
        InsertSort test = new InsertSort();
        int[] nums = {12, 30, 25, 9, 18, 1, 24, 3};
        test.insertSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
