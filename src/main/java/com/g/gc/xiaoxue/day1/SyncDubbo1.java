/**
 * Copyright (C), 2019,
 * FileName: SyncDubbo1
 * Author:   gc
 * Date:     2019/6/15 17:18
 * Description: synchronized 的重入
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.g.gc.xiaoxue.day1;

/**
 * 〈一句话功能简述〉<br> 
 * 〈synchronized 的重入〉 也是安全的
 *
 *
 * @author gc
 * @create 2019/6/15
 * @since 1.0.0
 */
public class SyncDubbo1 {
    public synchronized void method1(){
        System.out.println("method1..");
        method2();
    }
    public synchronized void method2(){
        System.out.println("method2..");
        method3();
    }
    public synchronized void method3(){
        System.out.println("method3..");
    }

    public static void main(String[] args) {
        final SyncDubbo1 sd = new SyncDubbo1();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sd.method1();
            }
        });
        t1.start();
    }
}