package com.leetcode.medium;

public class LeetCode1201 {
    public int nthUglyNumber(int n, int a, int b, int c) {
        long low = Math.min(Math.min(a, b), c);
        long high = low * n;

        long res = BinarySearch(low, high, a, b, c, n);
        long remianderA = res % a;
        long remianderB = res % b;
        long remianderC = res % c;
        res -= Math.min(Math.min(remianderA, remianderB), remianderC);
        return (int)res;
    }

    private  long getMcm(long a, long b) { // 求两数的最小公倍数,两数之积除以最大公约数
        long multi = a * b;
        while (b > 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return multi / a;
    }

    //二分搜索
    long BinarySearch(long low, long high, int a, int b, int c, long n){
        if(low >= high) return low;

        long mid = (low + high) >> 1;

        long MCM_a_b = getMcm(a, b);
        long MCM_a_c = getMcm(a, c);
        long MCM_b_c = getMcm(b, c);
        long MCM_a_b_c = getMcm(MCM_a_b, c);

        //独立的丑数个数为，当前数分别除以a、b、c的和，减去当前数除以a、b、c两两间最小公倍数的和，再加上当前数除以 a、b、c三者的最小公倍数
        long count_n = mid / a + mid / b + mid / c - mid / MCM_a_b - mid / MCM_b_c - mid / MCM_a_c +  mid / MCM_a_b_c;

        if(count_n == n) return mid;

        if(count_n < n) return BinarySearch(mid + 1, high, a, b, c, n);

        return BinarySearch(low,mid - 1, a, b, c, n);
    }
}
