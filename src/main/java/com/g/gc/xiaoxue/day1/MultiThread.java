/**
 * Copyright (C), 2019,
 * FileName: MultiThread
 * Author:   gc
 * Date:     2019/6/15 16:57
 * Description: 关键字
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.g.gc.xiaoxue.day1;

/**
 * 关键字synchronized取得的锁都是对象锁，而不是把一段代码（方法）当做锁，
 *  * 所以代码中哪个线程先执行synchronized关键字的方法，哪个线程就持有该方法所属对象的锁（Lock），
 *  *
 *  * 在静态方法上加synchronized关键字，表示锁定.class类，类一级别的锁（独占.class类）。
 *
 * @author gc
 * @create 2019/6/15
 * @since 1.0.0
 */
public class MultiThread {

    private static int num =0;

    // 这里 如果只是加了synchronized 由于是两个对象，持有的对象是不同。所以这个输出的结果可能不是正常想要的结果。
    // 但是如果加入了static 这个代码就是在类加载的时候就会加载，这个时候对象的锁就是类对象，所有的类对象都是一个所有都得获得这个锁才能执行代码。
    public static synchronized void printNum(String tag){
        try {
            if(tag.equals("a")){
                num = 100;
                System.out.println("tag a, set num over!");
                Thread.sleep(1000);
            } else {
                num = 200;
                System.out.println("tag b, set num over!");
            }

            System.out.println("tag " + tag + ", num = " + num);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final  MultiThread m1 = new MultiThread();
        final  MultiThread m2 = new MultiThread();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                m1.printNum("a");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                m2.printNum("b");
            }
        });

        t1.start();
        t2.start();

    }
}