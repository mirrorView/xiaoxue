/**
 * Copyright (C), 2019,
 * FileName: ChangeLock
 * Author:   gc
 * Date:     2019/6/15 17:25
 * Description: 锁对象的改变问题
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.g.gc.xiaoxue.day1.lock;

/**
 * 〈一句话功能简述〉<br> 
 * 〈锁对象的改变问题〉
 *
 * @author gc
 * @create 2019/6/15
 * @since 1.0.0
 */
public class ChangeLock {
    private String lock = "lock";

    private void method(){
        synchronized (lock) {
            try {
                System.out.println("当前线程 : "  + Thread.currentThread().getName() + "开始");
                lock = "change lock";
                Thread.sleep(2000);
                System.out.println("当前线程 : "  + Thread.currentThread().getName() + "结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        final ChangeLock changeLock = new ChangeLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        },"t2");
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}