package com.leetcode.medium.review;

import java.util.concurrent.Semaphore;

/**
 *两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 *
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: "foobar"
 * 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-foobar-alternately
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 知乎专栏：https://zhuanlan.zhihu.com/p/81626432
 * https://leetcode-cn.com/problems/print-foobar-alternately/solution/java-bing-fa-gong-ju-lei-da-lian-bing-by-kevinbaue/
 */
public class LeetCode1115 {
    private class FooBar {
        private int n;
        Semaphore foo = new Semaphore(1);
        Semaphore bar = new Semaphore(0);

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                foo.acquire();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                bar.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                bar.acquire();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                foo.release();
            }
        }
    }
}
