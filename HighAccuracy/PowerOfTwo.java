import java.util.Arrays;
import java.util.Scanner;

public class PowerOfTwo {
    /**
     * 计算2的给定次方
     *
     * @param n int类型的整数，代表给定次方
     * @return int类型的数组，存储2的n次方
     */
    public int[] powerOfTwo(int n) {
        int[] ans = new int[51]; // 存储2的n次方
        ans[50] = 1; // 计算基数为1
        int validIndex = 50; // 有效数字索引
        int carry; // 每次运算产生的进位
        int i;
        int j;
        // 乘2的次数为n次
        for (i = 1; i <= n; i++) {
            carry = 0; // 当前进位，初始为0
            // 从当前结果的最低位开始，每个数位都要和2相乘
            for (j = 50; j >= validIndex; j--) {
                ans[j] = ans[j] * 2 + carry; // 当前数位乘2，同时加上上一次的进位
                carry = ans[j] / 10; // 当前数位的十位数构成进位
                ans[j] = ans[j] % 10; // 当前数位仅保留个位数
            }
            if (carry > 0) { // 如果本次乘2运算产生更高一位的进位
                ans[--validIndex] = carry;
            }
        }
        // ans[validIndex]~ans[50]构成最终计算结果
        return Arrays.copyOfRange(ans, validIndex, 51);
    }

    public static void main(String[] args) {
        PowerOfTwo test = new PowerOfTwo();
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); // 输入的非负整数n
        int[] res = test.powerOfTwo(n); // 调用factorial方法计算n的阶乘
        // 遍历结果数组，输出阶乘的每一位数字
        for (int e : res) {
            System.out.print(e);
        }
        input.close();
    }
}

