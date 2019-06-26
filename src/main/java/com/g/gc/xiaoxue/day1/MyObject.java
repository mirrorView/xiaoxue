/**
 * Copyright (C), 2019,
 * FileName: MyObject
 * Author:   gc
 * Date:     2019/6/15 17:07
 * Description: 对象锁的同步和异步问题。
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.g.gc.xiaoxue.day1;

/**
 * 〈一句话功能简述〉<br> 
 * 〈对象锁的同步和异步问题。〉
 *
 * @author gc
 * @create 2019/6/15
 * @since 1.0.0
 */
public class MyObject {

    public synchronized  void method1(){
        try{
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(4000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void  method2(){
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        final  MyObject myObject = new MyObject();

        /**
         * 分析：
         * t1线程先持有object对象的Lock锁，t2线程可以以异步的方式调用对象中的非synchronized修饰的方法
         * t1线程先持有object对象的Lock锁，t2线程如果在这个时候调用对象中的同步（synchronized）方法则需等待，也就是同步
         */
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myObject.method1();
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                myObject.method2();
            }
        },"t2");

        t1.start();
        t2.start();
    }
}