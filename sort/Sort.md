```java
/**
 * 插入排序算法实现。
 *
 * @param nums 待排序的整型数组
 */
public void InsertSort(int[] nums) {
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
```
        ```java
/**
 * 冒泡排序算法实现。
 *
 * @param nums 需要排序的整数数组
 */
public void BubbleSort(int[] nums) {
    int n = nums.length; // 获取数组的长度
    // 外层循环控制排序的轮数，每一轮可以确保将一个最大（或最小）的元素移动到数组的一端
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n - i - 1; j++) { // 内层循环负责在每一轮中进行相邻元素的比较和交换
            // 如果当前元素大于它后面的元素，则交换它们的位置
            if (nums[j] > nums[j + 1]) { // 这样可以确保每一轮循环结束后，最大的元素被移动到当前未排序部分的最后
                int temp = nums[j]; // 使用临时变量来交换两个元素
                nums[j] = nums[j + 1];
                nums[j + 1] = temp;
            }
        }
    }
}
```
        ```java
/**
 * 交换数组中两个元素的位置
 *
 * @param nums 要交换元素的数组
 * @param i    第一个元素的索引
 * @param j    第二个元素的索引
 */
public void swap(int[] nums, int i, int j) {
    int temp = nums[i]; // 临时存储第一个元素的值
    nums[i] = nums[j]; // 将第二个元素的值赋给第一个元素
    nums[j] = temp; // 将临时存储的第一个元素的值赋给第二个元素
}

/**
 * 对数组进行划分，并返回基准元素（pivot）的最终位置
 *
 * @param nums 要划分的数组
 * @param i    划分的起始索引
 * @param j    划分的结束索引
 * @return 基准元素的最终索引
 */
public int Partition(int[] nums, int i, int j) {
    int pivotIndex = i; // 选择第一个元素作为基准元素
    while (i < j) { // 当起始索引小于结束索引时继续循环
        // 从后向前寻找第一个大于基准元素的值
        while (i < j && nums[j] >= nums[pivotIndex]) {
            j--;
        }
        // 从前向后寻找第一个小于基准元素的值
        while (i < j && nums[i] <= nums[pivotIndex]) {
            i++;
        }
        if (i < j) {
            swap(nums, i, j); // 交换找到的两个元素的位置
        }
    }
    swap(nums, pivotIndex, i); // 将基准元素放到正确的位置上（即划分好的两部分的中间）
    return i; // 返回基准元素的最终索引
}

/**
 * 使用霍尔快速排序算法对数组进行排序
 *
 * @param nums 要排序的数组
 * @param i    排序的起始索引
 * @param j    排序的结束索引
 */
public void HoareQuickSort(int[] nums, int i, int j) {
    if (i > j) { // 如果起始索引大于结束索引，则无需排序
        return; // 直接返回
    }
    // 对数组进行划分，并得到基准元素的最终索引
    int pivotIndex = Partition(nums, i, j);
    // 递归地对基准元素左侧的子数组进行排序
    HoareQuickSort(nums, i, pivotIndex - 1);
    // 递归地对基准元素右侧的子数组进行排序
    HoareQuickSort(nums, pivotIndex + 1, j);
}
```

