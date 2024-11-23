import java.util.Arrays;
import java.util.Scanner;

public class Factorial {
    /**
     * 计算非负整数的阶乘，并以整型数组的形式返回结果。
     *
     * @param n 非负整数，代表待求阶乘的整数
     * @return 整型数组，数组中的每个元素代表阶乘结果的一位数字
     */
    public int[] factorial(int n) {
        // 初始化一个足够大的数组来存储阶乘结果的每一位数字
        int[] ans = new int[5001];
        ans[5000] = 1; // 初始化ans[5000]=1,因为0!=0
        int validIndex = 5000; // 记录有效数字的下标
        for (int i = 1; i <= n; ++i) { // 从1开始计算到n的阶乘
            int carry = 0; // carry用于存储乘法运算中的进位
            for (int j = 5000; j >= validIndex; --j) { // 每乘依次都应该从个位开始计算
                ans[j] = ans[j] * i + carry; // 计算对应位数的乘积
                carry = ans[j] / 10; // 更新进位carry
                ans[j] %= 10; // 更新当前位的值（只保留个位数）
            }
            // 如果乘法运算后有进位，则需要将进位加到结果的更高位上
            while (carry > 0) { // 可能会多次进位
                ans[--validIndex] = carry % 10; // 将进位的个位数添加到更高位
                carry /= 10; // 更新进位（去掉已经添加的个位数）
            }
        }
        // ans[validIndex]~ans[5000]构成最终计算结果
        return Arrays.copyOfRange(ans, validIndex, 5001);
    }

    public static void main(String[] args) {
        Factorial test = new Factorial();
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); // 输入的非负整数n
        int[] res = test.factorial(n); // 调用factorial方法计算n的阶乘
        // 遍历结果数组，输出阶乘的每一位数字
        for (int e : res) {
            System.out.print(e);
        }
        input.close();
    }
}
